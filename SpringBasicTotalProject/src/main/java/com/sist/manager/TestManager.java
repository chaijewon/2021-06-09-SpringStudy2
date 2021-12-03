package com.sist.manager;

public class TestManager {
	/*public static void main(String[] args)
    {
          maTest();
          // string to extract keywords
          String strToExtrtKwrd = "마포에 있는 맛집 추천해 주세요";
          // init KeywordExtractor
          KeywordExtractor ke = new KeywordExtractor();
          // extract keywords
          KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
          // print result
          for( int i = 0; i < kl.size(); i++ ) {
                Keyword kwrd = kl.get(i);
                System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
          }
    }
    public static void maTest()
    {
          String string = "마포에 있는 맛집 추천해 주세요";
          try {
                MorphemeAnalyzer ma = new MorphemeAnalyzer();
                ma.createLogger(null);
                Timer timer = new Timer();
                timer.start();
                List<MExpression> ret = ma.analyze(string);
                timer.stop();
                timer.printMsg("Time");
                ret = ma.postProcess(ret);
                ret = ma.leaveJustBest(ret);
                List<Sentence> stl = ma.divideToSentences(ret);
                for( int i = 0; i < stl.size(); i++ ) {
                      Sentence st = stl.get(i);
                      System.out.println("=============================================  " + st.getSentence());
                      for( int j = 0; j < st.size(); j++ ) {
                            System.out.println(st.get(j));
                      }
                      
                }
                ma.closeLogger();
          } catch (Exception e) {
                e.printStackTrace();
          }
    }*/

}
