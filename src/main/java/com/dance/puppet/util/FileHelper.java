package com.dance.puppet.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Edit File Format
 * 
 * @author Chan Chen
 *
 */
public class FileHelper {
	public static ArrayList<String> fileToList(String path){
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> result = new ArrayList<String>();
		while(sc.hasNext())
			result.add(sc.next());
		sc.close();
		return result;
	}
	
	public static String bashToOneLine(String path){
		BufferedReader br;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader(path));
			while(br.ready()){
				sb.append(br.readLine()).append(';');
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
