
public class TransactionImp implements Transaction {
	private byte[] reciver;
	private int amount = 0;
	private byte[] Sender ;
	private byte[] signature = new byte[32];
	private KPair kpReciver =null;
	private KPair kpSender =null;

		public TransactionImp() {
			
		kpReciver =Utils.getKeyPair();
	    kpSender =Utils.getKeyPair();
	    Sender = kpSender.publicKey;
	    reciver = kpReciver.publicKey;
			
		}

		@Override
		public void setSender(byte[] sender) {
Sender = sender;			
		}

		@Override
		public void setReceiver(byte[] receiver) {
			reciver = receiver;
		}

		@Override
		public void setAmount(int amount) {
this.amount = amount;	
		}

		@Override
		public void setSignature(byte[] signature) {
			this.signature = signature;			
		}

		@Override
		public byte[] getSender() {
			return Sender;
		}

		@Override
		public byte[] getReceiver() {
			return reciver;
		}

		@Override
		public int getAmount() {
			return amount;
		}

		@Override
		public byte[] getSignature() {
			return signature;
		}

		@Override
		public void sign(byte[] pvk) {
		    byte[] key1 = Utils.concat(getSender() , getReceiver() );
					byte[] Amount = Utils.toBytes(amount);
					byte [] AK = Utils.concat(key1 , Amount);
								
					setSignature( Utils.sign(AK , pvk));			
		}

		@Override
		public boolean isSignatureValid() {
		    byte[] key1 = Utils.concat(getSender() , getReceiver() );
						byte[] Amount = Utils.toBytes(amount);
						byte [] AK = Utils.concat(key1 , Amount);
						boolean isValid = Utils.isSignatueValid(AK , signature , getSender() );
					return isValid;
		}

		@Override
		public byte[] toBytes() {
			byte[] key1 = Utils.concat(getSender() , getReceiver() );
			byte[] Amount = Utils.toBytes(amount);
			byte [] AK = Utils.concat(key1 , Amount);
			return Utils.concat(AK , this.signature);
		}



}
