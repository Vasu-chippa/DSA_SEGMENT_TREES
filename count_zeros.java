import java.util.*;
class  count_zeros {
    public static int tree[];
    public static void build(int i , int st, int end , int[] arr){
        if(st==end){
          tree[i] = (arr[st]==0) ? 1 : 0;
          return ;
        }
        int mid = (st+end)/2;
        build(2*i+1,st,mid,arr);
        build(2*i+2,mid+1,end,arr);
        tree[i] =tree[2*i+1] +tree[2*i+2];
    }
    public static  void update(int i, int st,int end, int[] arr, int x, int val){
        if(st==end){
            arr[x] =val;
            tree[i]=(arr[st]==0) ?1 : 0;
            return;
        }
        int mid=(st+end)/2;
        if(x<=mid) update(2*i+1,st,mid,arr,x,val);
        else update(2*i+2,mid+1,end,arr,x,val);
        tree[i] = tree[2*i+1]+tree[2*i+2];
    }
    public static int query(int i, int st, int end, int[] arr, int l, int r){
        if(l>end || r<st ) return 0;
        if( l<=st && end<=r)return tree[i];
        int mid =(st+end)/2;
        return query(2*i+1,st,mid,arr,l,r) + query(2*i+2,mid+1,end,arr,l,r);
    }
    
	public static void main (String[] args) {
	Scanner sc = new Scanner(System.in);
	int n=sc.nextInt();
	int[]arr = new int[n];
	for(int i=0;i<n;i++) arr[i]=sc.nextInt();
	tree=new int[4*n];
	build(0,0,n-1,arr);
	int q=sc.nextInt();
	while(q-- >0){
	    int t=sc.nextInt();
	    if(t==1){
	        int x =sc.nextInt();
	        int val=sc.nextInt();
	        update(0,0,n-1,arr,x,val);
	    }
	    else {
	        int l=sc.nextInt();
	        int r=sc.nextInt();
	        System.out.println(query(0,0,n-1,arr,l,r));
	    }
	}
	}
}
