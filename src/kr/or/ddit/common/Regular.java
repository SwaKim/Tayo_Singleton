package kr.or.ddit.common;

import java.util.regex.Pattern;

public class Regular {
   
   public boolean PatternId(String id){
      
      return Pattern.matches("^[0-9a-z-A-Z]{5,11}$", id);
   }
   
   public boolean PatternPassword(String password){
      return Pattern.matches("^(?=.*(\\W))(?=.*(\\w)).{2,10}$", password);
   }
   
   public boolean patternName(String name){
      return Pattern.matches("^[가-힣a-zA-Z]{2,7}$", name);
   }
   
   public boolean patternMoney(String money){
      return Pattern.matches("^[1-9][0-9]{3,5}$", money);
   }
   
   public boolean patternBusToBus(String bus){
      return Pattern.matches("^[가-힣]{2,4}-[가-힣]{2,4}$", bus);
   }
   
   public boolean patternBusKind(String bus){
      return Pattern.matches("^(우)(등)|(일)(반)$", bus);
   }
   
   public boolean patternNumberOfService(String numberOfService){
      return Pattern.matches("^[1-9]$", numberOfService);
   }
   
   public boolean patternEmail(String email){
      //return Pattern.matches("^[0-9a-zA-Z_]+@[A-Za-z]+(\\.[a-zA-Z]+)(\\.[a-zA-Z]+)*$", email);
      return Pattern.matches("^[0-9a-zA-Z_]+@"
            + "(([n][a][v][e][r])|([g][m][a][i][l]))"
            + "((\\.[c][o][m])|(\\.[c][o]\\.[k][r])|(\\.[n][e][t]))$", email);
   }
}