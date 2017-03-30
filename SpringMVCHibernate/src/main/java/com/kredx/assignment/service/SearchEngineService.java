package com.kredx.assignment.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.kredx.assignnment.model.ReviewDoc;

@Service
public class SearchEngineService {
	
	private ReviewDoc docs[];
	Map<String, HashSet<Integer>> hashMap;
	
	boolean isDocRead;
	
	boolean isDocReadComplete;
	
//	List<String> searchString = new ArrayList<String>();
	
	public void init() throws Exception {
		
		searchDoc();
//		searchString.add("dog");
//		searchString.add("bad");
//		searchString.add("good");
//		searchString.add("processed");
	}
	
	public List<ReviewDoc> getMatchingReviews(List<String> searchString) {
		
		if(!isDocRead) {
			try {
				isDocRead =true;
				init();
			}
			catch (Exception e) {
				System.out.println("sry");
				return null;
			}
		}
		
		
		while(!isDocReadComplete) {}
		
		ReviewDoc resultDoc[] = new ReviewDoc[50000];
		for(int s=0;s<50000;s++) {
			resultDoc[s] = new ReviewDoc(docs[s].getText(), docs[s].getSummary(), docs[s].getScore());
//			resultDoc[s].setMatches(0);
		}
		
		
//		int resultDocNum[] = new int[50000];
		
		for(int i=0;i<searchString.size();i++) {
			HashSet<Integer> hashSet = hashMap.get(searchString.get(i));
			
			Iterator<Integer> iterator = hashSet.iterator();
			
			while(iterator.hasNext()) {
				Integer resultdocNumber = iterator.next();
				int count = resultDoc[resultdocNumber-1].getMatches();
				resultDoc[resultdocNumber-1].setMatches(count+1);
			}
			
			
		}
		
		Arrays.sort(resultDoc,new ReviewComparator());
		
		List<ReviewDoc> resultAns = new ArrayList<ReviewDoc>();
		
		for(int k=0;k<20;k++) {
			resultAns.add(resultDoc[k]);
		}
		
		return resultAns;
	}
	
	public void searchDoc()  throws Exception{
		
//		ClassLoader cl = ClassLoader.getSystemClassLoader();
//		System.out.println();
		
		Resource resource = new ClassPathResource("/foods.txt");
		InputStream resourceInputStream = resource.getInputStream();
		InputStreamReader iReader = new InputStreamReader(resourceInputStream);
		BufferedReader br = new BufferedReader(iReader);
		
//		BufferedReader br = new BufferedReader(new FileReader());
		
//		BufferedReader br = new BufferedReader(new FileReader("C:/Users/Jhalak Patnaik/Downloads/finefoods.txt/foods.txt"));
		String str;
		
		isDocRead = true;
		System.out.println(new Timestamp(System.currentTimeMillis()));
		
		hashMap =new HashMap<String, HashSet<Integer>>();
		int docNumber=0;
		
		docs = new ReviewDoc[50000];
		String reviewSummaryTemp = "";
		int score = 0;
		
		while((str=br.readLine())!=null)  {
//		System.out.println(str);

			if(str.startsWith("review/summary:")){
				str = str.substring(15);
				str = str.trim();
				docNumber++;
				if(docNumber > 50000) {
					break;
				}
				reviewSummaryTemp = str;
				
				String words[] = str.split("[ !',.]");
				for(int i=0;i<words.length;i++) {
					if(words.length == 0)
						continue;
					if(hashMap.containsKey(words[i].toLowerCase())) {
						hashMap.get(words[i].toLowerCase()).add(docNumber);
					}
					else {
						HashSet<Integer> hashSet = new HashSet<Integer>();
						hashSet.add(docNumber);
						hashMap.put(words[i].toLowerCase(), hashSet);
					}
				}
			}
			else if(str.startsWith("review/text:")) {
				str = str.substring(12);
				str = str.trim();
				docs[docNumber-1] = new ReviewDoc(str, reviewSummaryTemp, score);
				String words[] = str.split("[ !',.]");
				for(int i=0;i<words.length;i++) {
					if(words.length == 0)
						continue;
					if(hashMap.containsKey(words[i].toLowerCase())) {
						hashMap.get(words[i].toLowerCase()).add(docNumber);
					}
					else {
						HashSet<Integer> hashSet = new HashSet<Integer>();
						hashSet.add(docNumber);
						hashMap.put(words[i].toLowerCase(), hashSet);
					}
				}
			}
			else if(str.startsWith("review/score:")) {
				String scoreStr = str.substring(14);
				score = (int)scoreStr.charAt(0); //Assuming the score are in between 0 and 5
				score = score-48; 
//				System.out.println(score);
				
				
			}
		}
		
		isDocReadComplete = true;
	}

}
