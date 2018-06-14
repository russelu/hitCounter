// hit counter
class HitCounter {

    /** Initialize your data structure here. */
    int[] count;
    final int N = 30;
    int sum;
    long lastTime;
    int lastPosition;
    public HitCounter() {
        count = new int[N];
        lastTime = 1;
        lastPosition = 0;
        sum = 0;
    }
    
    private void move() {
        long timestamp = System.currentTimeMillis();
        //long curTime = (long) (timestamp * 1e-6);
        long curTime = timestamp;
        long gap = Math.min(N, curTime - lastTime);
        for (long k = 0; k < gap; ++k) {
            lastPosition = (lastPosition + 1) % N;
            //System.out.println("removing last position: " + lastPosition + " = " + count[lastPosition]);
            sum -= count[lastPosition];
            count[lastPosition] = 0;
        }
        lastTime = curTime;
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit() {
        move();
        count[lastPosition]++;
        sum++;
        System.out.println(lastTime);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits() {
        move();
        return sum;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */

public class Main {
    public static void main(String[] args) {
        HitCounter obj = new HitCounter();
        for (int i = 0; i < 20; ++i) {
            obj.hit();
            try        
            {
                Thread.sleep(10);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
        }
        int param_2 = obj.getHits();
        System.out.println(param_2);
    }
}
