    import java.util.*;
    import java.io.*;
     
     
    public class Main {
        public static void solve() {
        	// your stuff here
        }
     
        public static void main(String args[] ) throws Exception {
            Thread t = new Thread(null, null, "~", 1<<20){
                @Override
                public void run() {
                    solve();
                }
            };
            t.start();
            t.join();
        }
    }
     
     