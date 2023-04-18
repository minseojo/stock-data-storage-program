# stock-data-storage-program

## 만든 이유
주식을 시작했는데, 주식을 시작하자마자 찜 해둔 주식 종류만 10개 넘었다. 근데 점점 늘어나는 찜 항목의 주식들을 내가 일일이 확인해서 메모해 둘 수는 없다.
그래서 크롤링을 통해 20230418.txt 형태로 하루마다 주가의 일일 데이터를 저장해 두려고 만들었다.   
- 주식의 이름 순 별로 저장 (ex) 가, 나, ..., 파, 하)
  

## 결과
![image](https://user-images.githubusercontent.com/64322765/232834100-3144099b-30e1-4701-9f74-53049fa8c88c.png)  

## 사용법
1. 깃허브 클론
2. NaverFinanceCrawler -> openFile()에 fileName을 원하는 파일 결과 주소로 변경  
3. ManageNaverStock -> addAllStocks()에 보고 싶은 주가 인스턴스추가      
                          
ex)   
Stock 이브이첨단소재 = new Stock("이브이첨단소재", "131400"); // 주식이름, 주식코드  
stocks.add(이브이첨단소재);
  
아래 링크를 보면 뒤에 코드부분이 이브이첨단소재의 주식코드 부분이다.   
https://finance.naver.com/item/sise.naver?code=131400 
