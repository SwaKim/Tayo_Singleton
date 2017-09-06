package kr.or.ddit.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Methods {
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();
	
	String[] str;
	int[] wordnum;
	String word;
	String word2;
	int num;
	
	Methods(int n){
		this.num = n;
	}
	
	void setWord() throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("D:\\A_TeachingMaterial\\1.BasicJava\\workspace\\ToyoSingleton\\word.txt"));
		int rdInt = rd.nextInt(2);
		int i = 0;
		while ((word = in.readLine())!=null) {
			if(i == rdInt){
				word2 = word;
				break;
			}
			i++;
			
		}
	}
	
	void setBlank() throws IOException{
		while(num+2>=word.length()){
			setWord();
		}
		
		str = new String[num];	//처리된 값의 글자 = 문자열 변수로 빈칸처리된 알파벳이 모이는 곳
		wordnum = new int[num];	//처리된 값의 인덱스=정수형 배열, 그 알파벳들의 위치값(index)
		
		for(int i=0; i<num; i++){
			int wordleng = rd.nextInt(word.length());
			
			char ch = word.charAt(wordleng);
			if(ch!='-'){
				str[i] = word.charAt(wordleng)+"";
				wordnum[i]=wordleng;
				
				word = word.substring(0, wordleng) +"-"+
						word.substring(wordleng+1, word.length());
			}else{
				i--;
			}
		}
		System.out.println(word);
	}
	
	void Play() throws IOException{
		int count = 1;
		
		LABEL:
		while (count<=5) {
			System.out.println("현재 "+(count++)+"라운드");
			String letter = sc.nextLine();
			String letterBig = letter.toUpperCase();
			String letterSmall=letter.toLowerCase();
			
			for (int i = 0; i < str.length; i++) {
				if (str[i].equals(letter)||str[i].equals(letterBig)||str[i].equals(letterSmall)) {
					//소문자든 대문자든 같은 글자로 처리
					
					String word3 = word;
					word = word.substring(0, wordnum[i])+str[i]+word.substring(wordnum[i]+1, word.length());
					//해당 '-'을(word[wordNum[i]] 해당문자로 교체하여 word에 다시 넣어준다.)
					
					for (int j = 0; j < str.length; j++) {
						if (str[j].equals(str[i])) {
							word = word.substring(0, wordnum[j])+str[j]
									+ word.substring(wordnum[j]+1, word.length());
						}
					}
					if(!word3.toUpperCase().equals(word.toUpperCase())){
						System.out.println(word);
					}
					if (word.equals(word2)) {
						System.out.println("정답입니다!");
						break LABEL;
					}
				}
			}
		}
	}
}
public class Hangman {
	public static void main(String[] args) throws IOException {		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("행맨 게임에 오신것을 환영합니다.");
			System.out.println("1.Easy");
			System.out.println("2.Medium");
			System.out.println("3.Hard");
			System.out.println("4.Ultra Hard");
			int level = sc.nextInt();
			
			Methods mt = new Methods(level + 1);
			mt.setWord();
			mt.setBlank();
			mt.Play();
			
			System.out.println("다시 시작하시겠습니까? (Y/N)");
			String YN = sc.next();
			if(YN.equals("N")||YN.equals("n")){
				break;
			}
			
		}
	}
}
