package ocp.homeword;

import java.util.Random;
import java.util.Scanner;

public class Identification {
	/*身份證產生器
	 * 1.輸入身份證號,驗證此身份證號是否正確
	 * 2.產生正確的身份證號
	 * 	a.選擇區域
	 * 	b.性別
	 */
	static String s="ABCDEFGHJKLMNPQRSTUVWXYZIO";
	static int checknum=0;
	static int y=9;
	public static void main(String[] args) {
		System.out.println("請輸入數字1或2\n1.請輸入身份證號\n2.產生一組新的身分證號");
		Scanner sc=new Scanner(System.in);
		int choose = sc.nextInt();//讓使用者選擇輸入身分證或產生身分證號
		if(choose==1) {
			int CheckID=checkID();
			if(CheckID%10==0) {
				System.out.println("輸入正確");
			}else {
				System.out.println("輸入錯誤，請重新輸入");
			}
		}else if(choose==2) {
			System.out.println("請輸入性別a或b:\na.男生\nb.女生");
			Scanner gd=new Scanner(System.in);
			String gender=gd.next();
			String GetNewID=getNewID(gender);
		}else {
			System.out.println("輸入錯誤，請重新輸入");
		}
	}
	
	static int checkID() {
		System.out.println("請輸入身分證");
		Scanner sc=new Scanner(System.in);
		String id = sc.next();//讓使用者輸入身分證
		int a=s.indexOf(id.charAt(0))+10;//身分證第一個英文轉數字
		String idenglish=String.valueOf(a);
		String id2=idenglish;
		for(int i=1;i<10;i++) {
			id2+=id.charAt(i);
		}
		char[] charArray = id2.toCharArray();
		for(int x=1;x<10;x++) {//做身分證第2~倒數第2個數的加總
			checknum+=(charArray[x]-48)*y--;
		}
		checknum+=charArray[0]+charArray[10]-96;//身分證第1和最後的數字加總
		return checknum;
	}
	static String getNewID(String gender) {
		Random rd=new Random();
		//產生英文後數字首位為1或2
		String randomStr;
		if(gender.equals("a")){
			randomStr="1";
		}else{
			randomStr="2";
		}
		//產生首位英文字A~Z
		char randomEng=(char)(Math.random()*26+65);
		//將A~Z轉為數字
		int randomEng3=s.indexOf(String.valueOf(randomEng))+10;
		
		String IDcheck=randomEng3+randomStr;//身分證驗證
		String ID=randomEng+randomStr;//身分證
		for(int x=0;x<8;x++) {
			Integer random=rd.nextInt(10);
			IDcheck+=random;
			if(x!=7)
				ID+=random;
		}
		char[] charArray=IDcheck.toCharArray();
		y=8;
		for(int x=2;x<10;x++) {//做身分證第2~倒數第2個數的加總
			checknum+=(charArray[x]-48)*y--;
		}
		checknum+=charArray[0]-48+(charArray[1]-48)*9;//身分證英文轉數字後的數字加總
		switch(checknum%10) {
		case 1:
			charArray[10]='9';
			break;
		case 2:
			charArray[10]='8';
			break;
		case 3:
			charArray[10]='7';
			break;
		case 4:
			charArray[10]='6';
			break;
		case 5:
			charArray[10]='5';
			break;
		case 6:
			charArray[10]='4';
			break;
		case 7:
			charArray[10]='3';
			break;
		case 8:
			charArray[10]='2';
			break;
		case 9:
			charArray[10]='1';
		}
		System.out.println("產生的身分證為:"+ID.concat(String.valueOf(charArray[10])));
		return ID;
	}

}
