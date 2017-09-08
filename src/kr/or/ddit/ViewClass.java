package kr.or.ddit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.bus.BusService;
import kr.or.ddit.bus.BusServiceImpl;
import kr.or.ddit.common.Regular;
import kr.or.ddit.common.Util;
import kr.or.ddit.hangman.HangManService;
import kr.or.ddit.hangman.HangmanServiceImpl;
import kr.or.ddit.member.MemberService;
import kr.or.ddit.member.MemberServiceImpl;
import kr.or.ddit.ticket.TicketService;
import kr.or.ddit.ticket.TicketServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TicketVO;


/**
 * @Class Name : ViewClass.java
 * @Description 콘솔 화면에 보여지는 프론트엔드 입출력을 담당
 * @author   이중우
 * @since 2017-08-30
 * @version 0.1
 * @see
 * 
 *<pre>
 *      수정일         수정자      수정내용      
 *   -------           -------      -------------------            
 *   2017.08.30         이중우      메인메뉴 시작
 *   2017.08.31         이중우      회원메뉴 작성
 *   2017.09.01         이중우      로그인 구간 체크
 *   2017.09.02         이중우      관리자메뉴
 *   2017.09.03         이중우      세부메뉴 기능 구현
 *   2017.09.04         김수환      노가다
 *   2017.09.05         이중우      정규식추가,api
 *Copyright (c) 2017 by DDIT  All right reserved
 * </pre>
 */
public class ViewClass {

   Regular r = new Regular();

   private TicketService ticketService = TicketServiceImpl.getInstance();
   private MemberService memberService = MemberServiceImpl.getInstance();
   private BusService busService = BusServiceImpl.getInstance();
   private HangManService hangman = HangmanServiceImpl.getInstance();

   static Scanner sc = new Scanner(System.in);
   int session = -1;                                             // 로그인 상태. -1 = 비회원
   MemberVO sessions = new MemberVO();

   // 메인메뉴
   void startMenu() {
      while (true) {
         clear();                                             //화면정리
         System.out.println("┏━━━━타요 버스에 오신것을 환영합니다.━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
         System.out.println("┃");
         System.out.println("┃\t1 : 로그인");
         System.out.println("┃\t2 : 회원가입");
         System.out.println("┃\t3 : HangMan!!");
         System.out.println("┃\t4 : 프로그램 종료");
         System.out.println("┃");
         System.out.print("┗━━━━━━━━원하는 메뉴의 숫자를 입력하세요 : ");
         String input = sc.next();
         switch (input) {
         case "1":
         case "로그인":
            login();
            break;
         case "2":
         case "회원가입":
            join();
            break;
         case "3":
         case "Hangman":
            hangmanGame();
            break;
         case "4":
         case "종료":
            System.out.println("\n"+new Date()+"\n프로그램이 종료 되었습니다.");
            System.exit(0);
         default:
            System.out.println("잘못된 입력입니다.");
            continue;
         }
      }
   }

   //회원가입
   private void join() {//입력은 맵타입으로 담아 인자값으로 전달
      MemberVO joinMemberVO = new MemberVO();
      clear();                                                //화면정리
      System.out.println("┏━━━━회원가입━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
      String userId;

      do {
         System.out.println("┃\t");
         System.out.println("┃(최소 5글자 최대 11글자)");
         System.out.print("┣━━━━아이디 : ");
         userId = sc.next();


         if (r.PatternId(userId)) {
            if (!memberService.checkJoinId(userId)) {                       // 아이디 중복일때 입력반복
               System.out.print("┃\tid중복입니다. 다시 아이디를 입력해주세요");
            } else {
               joinMemberVO.setMbUserId(userId);      // 중복되는 아이디가 없을때 가입진행;
               break;
            }
         } else {
            System.out.println("┃\t잘못된 문자를 입력하셨습니다.");
         }

      } while (true);

      // 비밀번호 입력구간 시작
      String userPw;
      String userPwChk = null;
      boolean pCheck = false;                                       // 비밀번호 중복체크         

      System.out.println("┃\t");
      System.out.println("┃(특문포함 최소 2글자 최대 10글자)");
      do {
         System.out.print("┣━━━━비밀번호 : ");
         userPw = sc.next();
         if (r.PatternPassword(userPw)) {
            System.out.println("┃");
            System.out.print("┣━━━━암호확인 : ");
            userPwChk = sc.next();
         } else {
            System.out.println("┃\t잘못된 문자를 입력하셨습니다.");
         }
         pCheck = userPw.equals(userPwChk);

      } while (!pCheck);
      joinMemberVO.setMbUserPw(userPw);
      // 비밀번호 입력구간 끝

      while(true){
         System.out.println("┃");
         System.out.println("┃(최소 2글자 최대 7글자)");
         System.out.print("┗━━━━이름 : ");
         String name = sc.next();

         if (r.patternName(name)) {                                          // 입력한 id가 정규식에 맞을경우 중복확인
            joinMemberVO.setMbUserName(name);
            break;
         } else {
            System.out.println("잘못된 문자를 입력하셨습니다.");
            continue;
         }
      }

      // 이메일 발송
      String email = null;
      while(true){
         System.out.println("gmail 또는 naver만 이용가능!");
         System.out.println("이메일을 입력해주세요.");
         email = sc.next();
         if (r.patternEmail(email)) {      
            break;
         } else {
            System.out.println("잘못된 형식의 이메일입니다.");
            continue;
         }

      }
      boolean joinMenu = memberService.joinMember(joinMemberVO);

      Util u = new Util();

      if (joinMenu) {
         System.out.println("회원가입이 완료되었습니다. 환영합니다.");
         u.email(email);
      } else {
         System.out.println("입력내용을 다시 한번 확인해 주세요.");
      }

   }






   // 로그인메뉴
   private void login() {
      Map<String, String> login = new HashMap<String, String>();

      clear();                                                //화면정리
      System.out.println("┏━━━━어서오세요. 아이디를 입력해주세요 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
      System.out.println("┃(최소 5글자 최대 11글자)");
      System.out.print("┣━━━━아이디 : ");
      String userId = sc.next();
      System.out.println("┃");


      if (r.PatternId(userId)) {
         login.put("userId", userId);
      }

      System.out.println("┃(특문포함 최소 2글자 최대 10글자)");
      System.out.print("┗━━━━비밀번호 : ");
      String userPw = sc.next();


      if (r.PatternPassword(userPw)) {
         login.put("userPw", userPw);
      }

      session = memberService.loginCheck(login);
      if (session == -3) {
         System.out.println("없는 아이디입니다.");
      } else if (session == -1) {
         System.out.println("비밀번호가 틀렸습니다.");
      } else if (session == -2) {
         adminMenu();                                          // 관리자메뉴
      } else {
         memberMenu();                                          // 회원메뉴
      }
   }

   // 회원메뉴
   private void memberMenu() {
      while (true) {
         clear();                                             //화면정리
         System.out.println("┏━━━━회원메뉴━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
         System.out.println("┃");
         System.out.println("┃\t1 : 버스 예매하기");
         System.out.println("┃\t2 : 구매 티켓 확인");
         System.out.println("┃\t3 : 포인트 충전");
         System.out.println("┃\t4 : 회원탈퇴");
         System.out.println("┃\t5 : 로그아웃");
         System.out.println("┃\t");
         System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");
         String input = sc.next();

         switch (input) {
         case "1":
            ticketing();                                       // 버스예매
            break;
         case "2":
            confirmTicket();                                    // 구매 티켓 확인
            break;
         case "3":
            chargeMoney();                                       // 충전
            break;
         case "4"://회원탈퇴
            boolean t = memberService.deleteMember(session);
            if(t){
               System.out.println("탈퇴되었습니다.");
               return;
            }else{
               System.out.println("탈퇴실패되었습니다.");
            }

            return;   
         case "5":
            return;

         default:
            System.out.println("잘못된 입력입니다.");
            continue;
         }
      }
   }

   // 버스예매하기
   private void ticketing() {
      TicketVO paidVo = new TicketVO();

      clear();                                                //화면정리
      System.out.println("┏━━━━버스예매━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
      System.out.println("┃");
      System.out.println("┃\t번호\t노선\t출발시간\t\t버스등급\t가격\t남은 좌석");
      boolean t =  busService.showBusList();
      if(t == false){
         System.out.println("┃");
         System.out.print("┗━━━━━━━━운행중인 버스가 없습니다.");
         return;
      }
      System.out.println("┃\t");
      System.out.println("┣━━━━목적지 선택");
      System.out.print("노선에 해당하는 번호를 선택해 주세요.");
      String bsRoute = sc.next();

      System.out.println("┗━━━━좌석 선택 (일반 45, 우등35) :");
      String seat = sc.next();
      
      paidVo.setMemIndex(session);
      paidVo.setSeatIndex(Integer.parseInt(seat));
      paidVo.setBusIndex(Integer.parseInt(bsRoute));
      
      int payCheck = ticketService.payBusTicket(paidVo);

      if (payCheck == -1) {
         System.out.println("해당 노선이 존재하지 않습니다");
      } else if (payCheck == -2) {
         System.out.println("좌석이 이미 판매되었습니다.");
      } else if (payCheck == -3) {
         System.out.println("잔액이 부족합니다.");
      } else {
         System.out.println("결제가 완료되었습니다.");
      }

   }

   // 구매티켓확인
   private void confirmTicket() {
      while (true) {
         clear();                                             //화면정리
         System.out.println("┏━━━━구매티켓확인  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
         System.out.println("┃");
         System.out.println("┃\t번호\t노선\t출발시간\t\t구매시간\t\t버스등급\t가격\t좌석 번호");
         boolean tl = ticketService.showTicketList(session);                           //로그인한 회원의 구매목록 출력
         if(tl == false){
            System.out.println("┃");
            System.out.print("┗━━━━━━━━예약하신 티켓이 없습니다.");
            return;
         }
         System.out.println("┃");
         System.out.println("┣━━━━메뉴");
         System.out.println("┃\t");
         System.out.println("┃\t1 : 티켓 취소하기");
         System.out.println("┃\t2 : 뒤로가기");
         System.out.println("┃\t");
         System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");
         String input = sc.next();

         switch (input) {
         case "1":

            System.out.print("취소할 티켓의 번호를 입력해주세요 : ");
            int tiket = sc.nextInt();
            int result = ticketService.refundTicket(session, tiket);
            if (result == -1) {
               System.out.println("해당티켓이 없습니다.");
            } else if (result == -2) {
               System.out.println("해당티켓의 구매자가 아닙니다.");
            } else {
               System.out.println("티켓이 환불되었습니다.");

               System.out.println("고객님께서 현재 보유하신 금액은 "+result+"원 입니다.");
            }

            break;
         case "2":
            return;
         default:
            System.out.println("잘못된 입력입니다.");
            continue;
         }
      }
   }

   // 포인트충전
   private void chargeMoney() {
      clear();                                                            //화면정리
      String inputMoney = "0";   
      System.out.println("┏━━━━포인트 충전━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
      System.out.println("┃");
      System.out.println("┃\t고객님께서 현재 보유하신 금액은 "+sessions.getMbUserMoney()+"원 입니다.");
      System.out.println("┃최소 천단위 최대 십만단위");
      System.out.print("┗━━━━선불 결제할 금액을 입력해 주세요 : ");
      try {
         while (Integer.parseInt(inputMoney)<=0) {                                          //양수만 입력 가능. 인출불가

            inputMoney = sc.next();
            if(!r.patternMoney(inputMoney)){
               System.out.println("양수 또는 숫자가 아닙니다.");
               return;
            }
         }
         sessions = memberService.chargeMoney(session, Integer.parseInt(inputMoney));            //충전 후 현재 잔액
      } catch (Exception e) {
         System.out.println("충전가능한범위를 넘었습니다.");
      }

      System.out.println("고객님께서 현재 보유하신 금액은 "+sessions.getMbUserMoney()+"원 입니다.");


   }

   // 관리자메뉴
   private void adminMenu() {
      while (true) {
         clear();                                             //화면정리
         System.out.println("┏━━━━어서오세요 관리자님  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
         System.out.println("┃");
         System.out.println("┃\t1 : 회원관리");
         System.out.println("┃\t2 : 노선관리");
         System.out.println("┃\t3 : 정산");
         System.out.println("┃\t4 : 로그아웃");
         System.out.println("┃\t");
         System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");
         String input = sc.next();

         switch (input) {
         case "1":
            manageMember();                                       // 회원관리 이동
            break;
         case "2":
            manageRoute();                                       // 노선관리 이동
            break;
         case "3":
            calc();                                             // 정산 이동
            break;
         case "4":
            return;
         default:
            System.out.println("잘못된 입력입니다.");
            break;
         }
      }

   }

   // 회원관리
   private void manageMember() {
      while(true){

         clear();                                                //화면정리
         System.out.println("┏━━━━회원관리━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
         System.out.println("┃");
         System.out.println("┃\t번호\tID\t이름\t잔고\t관리자");
         boolean t = memberService.showMemberList();
         if(t == false){
            System.out.println("┃");
            System.out.print("┗━━━━━━━━회원이 없습니다.");
            return;
         }
         System.out.println("┃");
         System.out.println("┣━━━━메뉴");
         System.out.println("┃\t");
         System.out.println("┃\t1 : 회원 삭제하기");
         System.out.println("┃\t2 : 뒤로가기");
         System.out.println("┃\t");
         System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");
         String input = sc.next();

         switch (input) {
         case "1":
            System.out.print("삭제할 회원의 번호를 입력하세요 : ");
            int delUserIndex = 0;

            try {
               delUserIndex = sc.nextInt();
               if (delUserIndex==0) {
                  System.out.println("메인 관리자는 삭제할 수 없습니다.");
                  continue;
               }
            } catch (Exception e) {
               System.out.println("숫자만 입력해 주세요.");
            }

            boolean UserCheck = memberService.deleteMember(delUserIndex);

            if (UserCheck) {
               System.out.println("삭제에 성공하셨습니다.");
            } else {
               System.out.println("다시한번 확인해 주세요.");
            }
            break;
         case "2":
            return;
         default:
            System.out.println("잘못된 입력입니다.");
            continue;
         }
      }
   }

   //노선관리메뉴
   private void manageRoute() {
      while (true) {
         clear();                                             //화면정리
         System.out.println("┏━━━━노선관리━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
         System.out.println("┃");
         System.out.println("┃\t번호\t노선\t출발시간\t\t버스등급\t가격\t좌석");
         busService.showBusList();
         System.out.println("┃");
         System.out.println("┣━━━━메뉴");
         System.out.println("┃");
         System.out.println("┃\t1 : 노선추가");
         System.out.println("┃\t2 : 노선삭제");
         System.out.println("┃\t3 : 노선변경");
         System.out.println("┃\t4 : 이전메뉴");
         System.out.println("┃");
         System.out.print("┗━━━━━━━━원하는 메뉴를 입력하세요 : ");

         String input = sc.next();
         switch (input) {
         case "1":
            addBus("추가"); // 노선추가
            break;
         case "2":
            removeBus("삭제"); // 노선삭제
            break;
         case "3":
            reBus(); // 노선변경
            break;
         case "4":
            return;

         default:
            System.out.println("잘못된 입력입니다.");
            continue;
         }
      }
   }

   //버스 추가-변경
   private void addBus(String methodKind) {                              //코드 재사용. 매개변수로 추가-변경 
      Map<String, String> temp = new HashMap<String, String>();

      clear();                                                //화면정리
      System.out.println("┏━━━━노선" + methodKind+"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
      System.out.println("┃");
      while(true){
         System.out.print("┣━━━━새로 운행할 노선을 입력해주세요 [출발지-도착지] : ");
         String bsRoute = sc.next();
         if(r.patternBusToBus(bsRoute)){
            temp.put("bsRoute", bsRoute);
            break;
         }else{
            System.out.println("잘못입력하셨습니다.");
            continue;
         }
      }
      System.out.println("┃");
      while (true) {
         System.out.print("┣━━━━가격을 입력해주세요 : ");
         String bsPrice = sc.next();
         if(r.patternMoney(bsPrice)){
            temp.put("bsPrice", bsPrice);
            break;
         }else{
            System.out.println("잘못된 가격을 입력하셨습니다");
            continue;
         }
      }
      System.out.println("┃");
      while(true){
         System.out.print("┣━━━━버스의 종류를 입력해주세요 : ");
         String bsKind = sc.next();
         if(r.patternBusKind(bsKind)){
            temp.put("bsKind", bsKind);
            break;
         }else{
            System.out.println("잘못된 정보를 입력하셨습니다.");
            continue;
         }
      }
      while(true){
         System.out.println("┃(1회에서 최대 9회)");
         System.out.print("┗━━━━운행 횟수를 입력해주세요 : ");
         String numberOfService = sc.next();
         if(r.patternNumberOfService(numberOfService)){
            temp.put("numberService", numberOfService);
            break;
         }else{
            System.out.println("잘못된 횟수입니다.");
            continue;
         }
      }

      boolean busCheck = busService.addBus(temp);

      if (busCheck) {
         System.out.println(methodKind +" 되었습니다.");
      } else {
         System.out.println("다시한번 확인해 주세요.");
      }
   }

   //버스 삭제-변경
   private void removeBus(String methodKind) {                           //코드 재사용. 매개변수로 삭제-변경 
      clear();                                                //화면정리
      System.out.println("┏━━━━노선"+methodKind+"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
      System.out.println("┃");
      System.out.println("┃\t번호\t노선\t출발시간\t\t버스등급\t가격\t좌석");
      busService.showBusList();
      System.out.println("┃");
      System.out.println("┗━━━━"+methodKind + "할 노선의 번호를 입력해주세요");
      int removeBusIndex = 0;

      try {
         removeBusIndex = sc.nextInt();
      } catch (Exception e) {
         System.out.println("숫자만 입력해 주세요.");
      }

      boolean busCheck = busService.removeBus(removeBusIndex);

      if (busCheck) {
         System.out.println(removeBusIndex+"번을 "+methodKind+"합니다.");
      } else {
         System.out.println("다시한번 확인해 주세요.");
      }
   }

   //버스 변경
   private void reBus() {
//      removeBus("변경");                                          // 노선삭제
      clear();                                                //화면정리
      System.out.println("┏━━━━노선변경━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
      System.out.println("┃");
      System.out.println("┃\t번호\t노선\t출발시간\t\t버스등급\t가격\t좌석");
      busService.showBusList();
      System.out.println("┃");
      System.out.println("┗━━━━변경할 노선의 번호를 입력해주세요 : ");
      int changeBusIndex = 0;

      try {
         changeBusIndex = sc.nextInt();
      } catch (Exception e) {
         System.out.println("숫자만 입력해 주세요.");
      }

      boolean busCheck = busService.changeBus(changeBusIndex);

      if (busCheck) {
         System.out.println(changeBusIndex+"번을 변경합니다. 현재 노선은 기록으로 남고 새로운 노선이 추가됩니다.");
      } else {
         System.out.println("다시한번 확인해 주세요.");
      }
      addBus("변경");                                             // 노선추가
   }

   //정산
   private void calc() {
      clear();                                                //화면정리
      System.out.println("┏━━━━ 정산   ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
      System.out.println("┃");
      System.out.println("┃\t번호\t노선\t출발시간\t\t구매시간\t\t버스등급\t좌석\t가격\t구매자");
      boolean t = ticketService.showTotalTicketList();                        //모든 티켓 열람
      if(t == false){
         System.out.println("┃");
         System.out.print("┗━━━━━━━━판매된 티켓이 없습니다.");
         return;
      }
      int sum = ticketService.calcTotal();                                 //티켓 총 판매금액
      System.out.println("┃");
      System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\t합계\t"+sum+"원");
   }

   /**
    * 회원DB-회원목록 [관리자모드]
    * @param 회원index
    * @return 멤버리스트
    * <pre>
    * 관리자모드에서 사용할 회원목록, 회원index를 받아 해당회원 정보를 출력합니다.
    * </pre>
    */
   private void clear(){
      for (int i = 0; i < 2; i++)   System.out.println("");   
   }

   public void hangmanGame() {      
      Scanner sc = new Scanner(System.in);

      while (true) {
         clear();
         System.out.println("┏━━━━ 행맨 게임에 오신것을 환영합니다. ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
         System.out.println("┃\t");
         System.out.println("┃\t1.Easy");
         System.out.println("┃\t2.Medium");
         System.out.println("┃\t3.Hard");
         System.out.println("┃\t4.Ultra Hard");
         System.out.println("┃\t");
         System.out.print("┗━━━━━━━━원하는 메뉴의 숫자를 입력하세요 : ");
         int level = sc.nextInt();
         hangman.setWord();
         hangman.setBlank(level + 2);
         hangmanPlay(hangman.setBlank(level + 2));                           //레벨을 입력받아 setBlank메서드 '빈칸수'를 늘립니다.

         System.out.println("다시 시작하시겠습니까? (Y/N)");
         String YN = sc.next();
         if(YN.equals("N")||YN.equals("n")){
            break;
         }

      }
   }

   public void hangmanPlay(Map<String, Object> newGame){
      String[] str = (String[]) newGame.get("str");
      String question = (String) newGame.get("question");
      String answer = (String) newGame.get("answer");
      int[] wordnum = (int[]) newGame.get("wordnum");
      int count = 1;

      System.out.println(question);
      LABEL:
         while (count<=5) {
            System.out.println("현재 "+(count++)+"라운드");
            System.out.println("글자를 입력하세요 : ");
            String letter = sc.next();
            String letterBig = letter.toUpperCase();
            String letterSmall=letter.toLowerCase();

            for (int i = 0; i < str.length; i++) {
               if (str[i].equals(letter)||str[i].equals(letterBig)||str[i].equals(letterSmall)) {
                  //소문자든 대문자든 같은 글자로 처리

                  String tempWord = question;
                  question = question.substring(0, wordnum[i])+str[i]+question.substring(wordnum[i]+1, question.length());
                  //해당 '-'을(word[wordNum[i]] 해당문자로 교체하여 word에 다시 넣어준다.)

                  for (int j = 0; j < str.length; j++) {
                     if (str[j].equals(str[i])) {
                        question = question.substring(0, wordnum[j])+str[j]
                              + question.substring(wordnum[j]+1, question.length());
                     }
                  }
                  if(!tempWord.toUpperCase().equals(question.toUpperCase())){
                     System.out.println(question);
                  }
                  if (question.equals(answer)) {
                     System.out.println("정답입니다!");
                     break LABEL;
                  }
               }
            }
         }
   }

}