# CryptoTrading

1.  Run Crypto Trading System project
    $ cd cryptotradingsystem
    $ mvn spring-boot:run
    
2.  Test API calls

    	1.	Add User : curl -v -X POST localhost:8080/users -H "Content-type:application/json" -d "{\"userName\":\"John\",\"email\":\"john.gmail.com\",\"password\":\"xxx1234\",\"walletBalance\":\"50000\"}"
			
    	2.	Get Balance : curl -v localhost:8080/users/1/balance
			
    	3.	Get best aggregateePrice : curl -v localhost:8080/crypto/bestAggregatedPrice
			
    	4.	Get Trade History : curl -v localhost:8080/trade/1/trades
			
    	5.	Make Trade : curl -v -X POST localhost:8080/trade/1/1?tradeType=BUY&quantity=1
