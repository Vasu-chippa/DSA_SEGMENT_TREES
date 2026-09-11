import java.util.*;
public class gcd {
    public static int tree[];
    public static void build(int index,int st,int ed,int arr[]) {
        if(st==ed){
            tree[index] = arr[st];
            return;
        }
        int mid = (st+ed)/2;
        build((2*index)+1,st,mid,arr);
        build((2*index)+2,mid+1,ed,arr);
        tree[index] = gcdn(tree[2*index+1] , tree[2*index+2]);
    }
    static int gcdn(int a , int b){
        if(b==0) return a;
        return gcdn(b,a%b);
    }
    public static void update(int index,int st,int ed,int arr[],int x,int val) {
        if(st==ed) {
            arr[x] = val;
            tree[index] = val;
            return;
        }
        int mid=(st+ed)/2;
        if(x<=mid)
            update(2*index+1,st,mid,arr,x,val);
        else
            update(2*index+2,mid+1,ed,arr,x,val);
        tree[index] = gcdn(tree[2*index+1] , tree[2*index+2]);
    }
    public static int query(int index,int st,int ed,int i,int j) {
        if(i > ed || j < st)
            return 0;
        if(i<=st && ed<=j)
            return tree[index];
        int mid = (st+ed)/2;
        return gcdn( query(2*index+1,st,mid,i,j) , query(2*index+2,mid+1,ed,i,j));
    }
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		tree = new int[4*n];
		int arr[] = new int[n];

		for(int i=0;i<n;i++) arr[i] = sc.nextInt();
		build(0,0,n-1,arr);
		int Q = sc.nextInt();
		for(int i=0;i<Q;i++) {
		    int type = sc.nextInt();
		    if(type==1)  {
		        int index = sc.nextInt();
		        int new_val = sc.nextInt();
		        update(0,0,n-1,arr,index,new_val);
		    }
		    else {
		        int st = sc.nextInt();
		        int ed = sc.nextInt();
		        System.out.println(query(0,0,n-1,st,ed));
		    }
		}
	}
}
