// hit counter
class Solution {
    
    class HitCounter {
        
        int[] count;
        int lastTime;
        int lastPosition;
        int sum;
        final int N;
        
        public HitCounter(int N) {
            this.N = N;
            count = new int[N];
            lastTime = 1;
            lastPosition = 0;
            sum = 0;
        }
        
        private move(int t) {
            int gap = Math.min(t - lastTime, N);
            for (int k = 0; k < gap; ++k) {
                lastPosition = (lastPosition + 1) % N;
                sum -= count[lastPosition];
                count[lastPosition] = 0;
            }
            lastTime = t;
        }
        
        public void hit(int timestamp) {
            move(timestamp);
            sum++;
            count[lastPosition]++;
        }
        
        public int getHits(int timestamp) {
            move(timestamp);
            return sum;
        }
    }
    
    public static void main(String[] args){
        HitCounter hc = new HitCounter(Integer.parseInt(args[0]));
        for (int i = 1; i < args.length; ++i) {
            if (args[i++].equals("hit")) {
                hc.hit(Integer.parseInt(args[i]));
            } else {
                hc.getHits(Integer.parseInt(args[i]));
            }
        }
    }
}
