
enum Browser{
    FIREFOX,
    CHROME,
    OPERA,
    EDGE,
    SAFARI,
    OTHER
}

enum OperationSystem{
    WINDOWS,
    MAC,
    LINUX,
    OTHER
}


public class UserAgent {
    final Browser browser;
    final OperationSystem operationSystem;
    private Boolean isBot;


    public Boolean getBot() {
        return isBot;
    }

    public UserAgent(String str) {
        String str1 = str.toLowerCase();
        String s;
        if (str.length()>1 && str.contains("/")){
            s = str1.substring(0, str.indexOf("/"));
        } else s=str1;

            switch (s) {
                case ("mozilla"):
                    this.browser = Browser.FIREFOX;
                    break;
                case ("chrome"):
                    this.browser = Browser.CHROME;
                    break;
                case ("opera"):
                    this.browser = Browser.OPERA;
                    break;
                case ("edge"):
                    this.browser = Browser.EDGE;
                    break;
                case ("safari"):
                    this.browser = Browser.SAFARI;
                    break;
                default:
                    this.browser = Browser.OTHER;
            }

            // "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0"

            if (str1.contains("windows")) {
                this.operationSystem = OperationSystem.WINDOWS;
            } else if (str1.contains("linux")) {
                this.operationSystem = OperationSystem.LINUX;
            } else if (str1.contains("mac")) {
                this.operationSystem = OperationSystem.MAC;
            } else this.operationSystem = OperationSystem.OTHER;

            if (str1.contains("bot")) {
                this.isBot=true;
            }
            else this.isBot=false;
        }


    public Browser getBrowser() {
        return browser;
    }

    public OperationSystem getOperationSystem() {
        return operationSystem;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "browser=" + browser +
                ", operationSystem=" + operationSystem +
                '}';
    }
}




    //"Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5X Build/MMB29P)
    // AppleWebKit/537.36 (KHTML, like Gecko)
    // Chrome/105.0.5195.125 Mobile Safari/537.36 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"



    //Mozilla/5.0 (platform; rv:gecko-version) Gecko/gecko-trail Firefox/firefox-version

