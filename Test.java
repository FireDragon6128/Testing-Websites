    
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

      
    public class First {  
      
        public static void main(String[] args) throws InterruptedException {  
            
        	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver\\chromedriver.exe");  
        	WebDriver driver=new ChromeDriver();  
           
        	driver.navigate().to("https://www.bits-pilani.ac.in/");
        	//driver.manage().window().maximize();
        	String[] links = null;
        	int linksCount = 0,workingLinks=0,deadLinks=0;

        	List<WebElement> linksize = driver.findElements(By.tagName("a")); 
        	linksCount = linksize.size();
        	System.out.println("Total no of links Available: "+linksCount);
        	links= new String[linksCount];
        	System.out.println("List of links Available: "); 
        	
        	for(int i=0;i<linksCount;i++){
        		links[i] = linksize.get(i).getAttribute("href");
        		System.out.println(linksize.get(i).getAttribute("href"));
        	} 
        	System.out.println('\n');
        	
        	for(int i=0;i<linksCount;i++){
        		try{
        			 
        			 URL link = new URL(links[i]);
        			 
        			 HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
 
        			 httpConn.setConnectTimeout(5000);
        			 
        			 httpConn.connect();
        			 
        			 if(httpConn.getResponseCode()== 200) {
        				 workingLinks++;
        				 //System.out.println("Link "+i+ " is working...");
        				 long totalTime=0;
        	        		for(int p=0;p<5;p++) {
        	        			long start = System.currentTimeMillis();
        	        			driver.navigate().to(links[i]);
        	        			long finish = System.currentTimeMillis();
        	        			totalTime+= finish - start;
        	        			Thread.sleep(100);
        	        		}
        	        		//System.out.println("Total and Average Time for link to load - " + totalTime+" " + totalTime/5);
        	        		//System.out.println("Link load time (average of 5 tries) - "+ totalTime/5+" ms");
        	        		System.out.println(totalTime/5);
        	        		//System.out.println('\n');
        			 }
        			 else {
        				 deadLinks++;
        				 //System.out.println("Link "+links[i]+" is dead and has Error: " + httpConn.getResponseCode());
        				 //System.out.println("Link "+i+" is dead and has Error: " + httpConn.getResponseCode());
        				 System.out.println("0");
        				 //System.out.println('\n');
        			 }
        		} 
        			 catch (Exception e) {
        				 deadLinks++;
        			 //e.printStackTrace();
        			 System.out.println("0");
        			 }
        	
        	}
        	
        	System.out.println("Total working links are "+workingLinks);
        	//System.out.println('\n');
        	System.out.println("Total dead links are "+deadLinks);
        	//System.out.println('\n');
        	System.out.println("Website Score is "+((workingLinks-deadLinks)/linksCount));
        	//System.out.println('\n');
    		driver.quit();
        }  
      
    }