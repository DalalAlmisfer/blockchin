
public class BlockImp implements Block {

	private byte[] miner ;
	private int nonce ;
	private Transaction trans;
	private byte[] hash = new byte[32];
	private byte[] Phash =  new byte[32];
	KPair kp ;

		public BlockImp() {
			trans = null;
			this.nonce = 0;
			kp = Utils.getKeyPair();
		//	miner = kp.publicKey;
			
	
}

		@Override
		public byte[] getMiner() {
			// TODO Auto-generated method stub
			return miner;
		}

		@Override
		public int getNonce() {
			return nonce;
		}

		@Override
		public Transaction getTransaction() {
			return trans;
		}

		@Override
		public byte[] getHash() {
			return hash;
		}

		@Override
		public byte[] getPrevHash() {
			return Phash;
		}

		@Override
		public void setMiner(byte[] miner) {
			this.miner = miner;
		}

		@Override
		public void setNonce(int nonce) {
this.nonce = 	nonce;		
		}

		@Override
		public void setTransaction(Transaction transaction) {
trans = transaction;			
		}

		@Override
		public void setPrevHash(byte[] prevHash) {
Phash = prevHash;			
		}

		@Override
		public void setHash(byte[] hash) {
this.hash = hash ;			
		}

		@Override
		public boolean isHashValid() {
			compHash(); //
			boolean isValid = Utils.validBlockHash(getHash());
			return isValid;
		}

		@Override
		public void compHash() {

			byte[] p = toBytes();
			if ( p == null ) return ; 
			setHash(Utils.getHash(p));			
		}

		@Override
		public void mine() {
			int n = 0; int zero = 0;
			while (n >= zero ) {
				setNonce(n);
				compHash(); 
			if ( isHashValid() ) 
				break;
				n +=1;

 		}			
		}

		@Override
		public byte[] toBytes() {
			byte[] Nonce = Utils.toBytes(getNonce());
			if ( getTransaction() == null) {
				return Utils.concat(Nonce , getPrevHash());
			}
			else {
			byte[] NT = Utils.concat(Nonce , trans.toBytes());
			byte [] resualt = Utils.concat(NT , getPrevHash());
			return resualt;
		}
		}
		
}
