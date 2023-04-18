# stock-data-storage-program

## 만든 이유
주식을 시작했는데, 주식을 시작하자마자 찜 해둔 주식 종류만 10개 넘었다. 근데 점점 늘어나는 찜 항목의 주식들을 내가 일일이 확인해서 메모해 둘 수는 없다.
그래서 크롤링을 통해 2023-04-18.txt 형태로 하루마다 주가의 일일 데이터를 저장해 두려고 만들었다.   
- 주식의 이름 순으로 저장 (ex) 가, 나, ..., 파, 하)
  

## 결과
![image](https://user-images.githubusercontent.com/64322765/232842136-b42f8e1a-b482-4591-b34a-03377bd5b6d5.png)  

## 사용법
1. 깃허브 클론
2. 결과가 저장 될 파일위치 변경: NaverFinanceCrawler - FILE_PATH_FORMAT 초기화
3. enum Stock - 데이터 저장을 원하는 주식 인스턴스 추가                    
ex)   
EV_ADVANCED_MATERIALS("이브이첨단소재", "131400"), // 주식이름, 주식코드
  
아래 링크를 보면 뒤에 코드부분이 이브이첨단소재의 주식코드 부분이다.   
https://finance.naver.com/item/sise.naver?code=131400 
