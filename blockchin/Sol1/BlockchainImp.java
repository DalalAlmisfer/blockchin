import java.util.Arrays;

public class BlockchainImp implements Blockchain {
	private int length = 0;
	private List<Block> l ; 
	
	public BlockchainImp() {
		l = new LinkedList<Block>();
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return length;
	}

	@Override
	public byte[] getLastBlockHash() {
		if ( l.empty() ) {
			return initHash;}
		
		l.findFirst();
		if (! l.last()) {
			l.findNext();
		}
		byte[] LastHash = l.retrieve().getHash();
		//if ( l.retrieve().getTransaction() == null)
		if ( l.retrieve().getTransaction() != null ) {
			if (Arrays.equals(LastHash , initHash))
				return null;
		}
		return LastHash;
	}

	@Override
	public boolean addBlock(Block b) {
		boolean flage = false;
		if (l.empty()) {
			b.setPrevHash(initHash);
			l.insert(b); length ++;
			flage = true; }
		else if (b.getTransaction() != null){
			l.findFirst();
			while (!l.last()) {
				l.findNext();
			}
			b.setPrevHash(getLastBlockHash());
			l.insert(b); length ++;
			flage = true ;
		}
		if (!l.empty() && b.getTransaction() == null) { return false ;}

		return flage;

	}

	@Override
	public List<Block> getBlocks() {
		return l;
	}

	@Override
	public int getBalance(byte[] pbk) {
		if (l.empty()) { return 0; }
		byte [] t; 
		int pbkLength = pbk.length;
		int TBalance = 0;
		boolean FisrtCase , SecCase , ThirdCase ;
		int m = minerReward;
		l.findFirst();
		while ( !l.last()) {
 FisrtCase = true ; SecCase = true ; ThirdCase = true ;
		Block First = l.retrieve();
           t = First.getMiner();
  if (Arrays.equals(pbk ,t) == false || t.length != pbkLength ) 
			FisrtCase = false;
		if (FisrtCase == true ) 
			TBalance += minerReward;
			if (TBalance < 0 ) 
				return -1;
//
			
	if( l.retrieve().getTransaction() != null ) {
		
	t = l.retrieve().getTransaction().getSender();
   if( t.length != pbkLength || Arrays.equals( pbk , t) == false ) 
	  SecCase = false ;
   if( SecCase == true ) {
	   int b = l.retrieve().getTransaction().getAmount() ;
	   TBalance -= b; }
   if( TBalance < 0)
	return -1;
		//
			t = l.retrieve().getTransaction().getReceiver();
			if (t.length != pbkLength || Arrays.equals(pbk , t) == false) 
				ThirdCase = false;
			if (ThirdCase == true ) {
				int a = l.retrieve().getTransaction().getAmount();
				TBalance += a; }
			if (TBalance < 0 ) 
					return -1;
				
			}
				l.findNext();
		}

		
		if (l.last()) {
			 FisrtCase = true ; SecCase = true ; ThirdCase = true ;
		Block First = l.retrieve();
           t = First.getMiner();
  if (Arrays.equals(pbk ,t) == false || t.length != pbkLength ) 
			FisrtCase = false;
		if (FisrtCase == true ) 
			TBalance += m;
			if (TBalance < 0 ) 
				return -1;
//
			
	if( l.retrieve().getTransaction() != null ) {
		
	t = l.retrieve().getTransaction().getSender();
   if( t.length != pbkLength || Arrays.equals( pbk , t) == false ) 
	  SecCase = false ;
   if( SecCase == true ) {
	   int b = l.retrieve().getTransaction().getAmount() ;
	   TBalance -= b; }
   if( TBalance < 0)
	return -1;
		//
			t = l.retrieve().getTransaction().getReceiver();
			if (t.length != pbkLength || Arrays.equals(pbk , t) == false) 
				ThirdCase = false;
			if (ThirdCase == true ) {
				int a = l.retrieve().getTransaction().getAmount();
				TBalance += a; }
			if (TBalance < 0 ) 
					return -1;
			
		}
		}
		
		return TBalance;
	}

	@Override
	public void removeInvalid() {
		   int countNotv = 0;
           if( l.empty() ) return ;
l.findFirst();
Block first =l.retrieve() ; 
//boolean beforeLast = l.last();


while(!l.last()) {
if( first != l.retrieve() ){
if( l.retrieve().isHashValid() == false || l.retrieve().getTransaction() == null || l.retrieve().getTransaction().isSignatureValid() == false ) {
		   break ;
}  else {
	        Transaction t = l.retrieve().getTransaction();
	        int a = l.retrieve().getTransaction().getAmount();
		    int SBalance = getBalance( t.getSender() ) ;
		   if( SBalance < a || SBalance < 1 )
		   break;   } 
}
l.findFirst();
int a = 0;
while(a < countNotv ) {
	l.findNext();
a++;}
			countNotv++; 

}
				
if(l.last()) {
	
if( first != l.retrieve()){
if(  l.retrieve().isHashValid() == false || l.retrieve().getTransaction() == null || l.retrieve().getTransaction().isSignatureValid() == false )
{ 
	l.remove() ;
    length-- ; 
} else {
		  Transaction tran = l.retrieve().getTransaction() ; 
		   int SBalance = getBalance( tran.getSender() ) ;
		   int a = l.retrieve().getTransaction().getAmount();
		   if( SBalance < a || SBalance < 1 )
		   {
		   l.remove(); 
		   length--; } 
		   } 
}
			}
if ( ! l.last () ) {
 l.findFirst();      
		   int loop = 0 ;
		   int x = countNotv - 1;
			while(loop < x) 
			{
			l.findNext(); loop++; 
			}
		 
			int m = length();
			while(countNotv <= m ) {
			l.remove();
		   length--;
		   countNotv++; }
					
		   }
		
	}


}
