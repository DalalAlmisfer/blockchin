

public class TreeSearchHistory implements SearchHistory {	
	public TSHNode root; // Do not change
	public int n; // Do not change
   
	public TreeSearchHistory() {  // Do not change
		root = new TSHNode();
		n = 0;
	}	
	// Change the code staring from this point
	@Override
	public int size() {
		return n;
	}
   public int length(List<Pair<Character, TSHNode>> r)
    {
    if(r.empty()) { return 0;}   
    int count =0;
    r.findFirst();
    while(!r.last())
    {
        count++;
        r.findNext();
    }
     count++;
     return count;
    }
        public void add(String word)
        {
            if(word.length()==0) return ;
            if(!findWord(word)){
            add(root,word,0);
            n++;
            }
            
        }
	private void add(TSHNode s,String word,int i) 
        {
            if(i==word.length()) 
            {
               return ;
            } 
        List<Pair<Character, TSHNode>>L= s.children;           
        if(L.empty())
        {                      
        Character firstChar=word.charAt(i);
    Pair<Character, TSHNode>firstPair=new  Pair<Character, TSHNode>(firstChar,new TSHNode());
        L.insert(firstPair);
        }
        else
        {              
             boolean found=false;
             int size=Length(L);
            L.findFirst();
           for(int t=1;t<size;t++)     {
               if(L.retrieve().first==word.charAt(i)){ 
                found= true;
                break;
                }
            L.findNext();            
            }   
           if(L.retrieve().first==word.charAt(i))
                found= true;
             if(!found){
               Character curr=word.charAt(i);
              TSHNode node =  new TSHNode();
                Pair<Character, TSHNode> Pair=new  Pair<Character, TSHNode>(curr, node);
                L.insert( Pair);             
                }
              }
              if(i==word.length()-1) 
                {               
                L.retrieve().second.end=true;
                }
        add(L.retrieve().second, word, i+1);
	}
	
        public boolean findWord(String word)
        {
        List<Pair<Character, TSHNode>> s = root.children;
         return findWord(root,word, 0);
        }
	private boolean findWord(TSHNode s,String word,int i) {
            if(s.end&&i==word.length())  return true; 
            if(!s.end&&i==word.length()) return false;
            List<Pair<Character, TSHNode>>L=s.children;
           if(L.empty()) return false;
             boolean found=false;
             int size=length(L);
            L.findFirst();
           for(int t=1;t<size;t++)     {
               if(L.retrieve().first==word.charAt(i)){ 
                found= true;
                break;
                }
            L.findNext();            
            }   
           if(L.retrieve().first==word.charAt(i))
                found= true;          
            if(!found)  return false;          
             else
            return findWord(L.retrieve().second, word, i+1);          
	   
	}

	
        public boolean findPrefix(String prefix)
        {
         if(prefix.length()==0) return true;        
        return findPrefix(root,prefix, 0);
        }
        private boolean findPrefix(TSHNode s,String prefix,int i)
        {                   
           if(i==prefix.length()&&p.children.empty()) 
            return false;
           if(i==prefix.length())  
           return true;                    
            List<Pair<Character, TSHNode>>L=s.children;
            if(L.empty()) return false;
             boolean found=false;
              int size=length(L);
            L.findFirst();
           for(int t=1;t<size;t++)     {
               if(L.retrieve().first==prefix.charAt(i)){ 
                found= true;
                break;
                }
            L.findNext();            
            }   
           if(L.retrieve().first==prefix.charAt(i))
                found= true;                   
            if(!found)  return false;          
             else
            return findPrefix(L.retrieve().second, prefix, i+1);	
        }
        	
	public List<String> complete(String prefix) {
            List<String>result=new LinkedList<String>();
            collect_words(root,prefix,"",result,0);           
		return result;
	}
        public void words(TSHNode p,String prefix,String word,List<String>result,int i)
        {
           if(s.end&&i>prefix.length()) 
           { 
               result.insert(word); 
           }  
         List<Pair<Character, TSHNode>>L=s.children;
          if(L.empty())
          {
            return ;   
          }                   
            if(i<prefix.length()) {      
             boolean found=false;
             int size=length(L);
            L.findFirst();
           for(int t=1;t<size;t++)     {
               if(L.retrieve().first==prefix.charAt(i)){ 
                found= true;
                break;
                }
            L.findNext();            
            }   
           if(L.retrieve().first==prefix.charAt(i))
                found= true;   
            if(!found)  { return;}                     
                word+=L.retrieve().first;
                words(L.retrieve().second, prefix, word, result, i+1);       }
            else
            {
             int size=length(L);
             L.findFirst();
           for(int t=1;t<=size;t++)
            {
                 word+=L.retrieve().first;
                words(L.retrieve().second, prefix, word, result, i+1);
                word=word.substring(0,word.length()-1);
            L.findNext();            
            }
              
            }
        }
    
}
