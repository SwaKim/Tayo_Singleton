package kr.or.ddit.hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import kr.or.ddit.TayoApplication;

public class HangmanServiceImpl implements HangManService{

	private static HangManService hm = null;
	
	public static HangManService getInstance(){
		if(hm == null){
			hm = new HangmanServiceImpl();
		}
		return hm;
	}
	
	Random rd = new Random();
	
	
	public String setWord(){
		String path = TayoApplication.class.getResource("").getPath(); // 현재 클래스의 절대 경로를 가져온다.
		//System.out.println(path); //--> 절대 경로가 출력됨
		File fileInSamePackage = new File(path + "word.txt");		// path 폴더 내의 test.txt 를 가리킨다.
		BufferedReader in;
		String answer = "";											// 정답을 설정합니다.
		try {
			in = new BufferedReader(new FileReader(fileInSamePackage+"\\..\\..\\..\\..\\..\\word.txt"));
			int rdInt = rd.nextInt(4);
			int i = 0;
			String wordFromFile;
			while ((wordFromFile = in.readLine())!=null) {
				if(i == rdInt){
					answer = wordFromFile;
					break;
				}
				i++;

			}
		} catch (FileNotFoundException e) {
			System.err.println("단어장을 읽어오는데 오류가 발생했습니다. 경로를 확인하세요");
		} catch (IOException e) {
			System.err.println("단어장을 읽어오는데 오류가 발생했습니다. 파일 내용을 확인하세요");
		}
		return answer;
	}
	
	public Map<String, Object> setBlank(int n){
		Map<String, Object> newGame = new HashMap<>();
		String answer = setWord();
		while(n+2>=answer.length()){	//입력 레벨보다(빈칸) 글자수가 작을시 단어를 재생성
			answer = setWord();
		}

		String question = answer;
		String[] str = new String[n];	//처리된 값의 글자 = 문자열 변수로 빈칸처리된 알파벳이 모이는 곳
		int[] wordnum = new int[n];		//처리된 값의 인덱스=정수형 배열, 그 알파벳들의 위치값(index)
		
		for(int i=0; i<n; i++){
			int wordleng = rd.nextInt(question.length());
			
			char ch = question.charAt(wordleng);
			if(ch!='-'){
				str[i] = question.charAt(wordleng)+"";
				wordnum[i]=wordleng;
				
				question = question.substring(0, wordleng) +"-"+
						question.substring(wordleng+1, question.length());
			}else{
				i--;
			}
		}
		newGame.put("str", (Object)str);
		newGame.put("answer", (Object)answer);
		newGame.put("question", (Object)question);
		newGame.put("wordnum", (Object)wordnum);
		return newGame;
	}
}
