package sourcepack;

import java.security.*;
import com.google.gson.*;
import java.util.*;
import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import org.bouncycastle.*;

public class ArtistChain {
	public static ArrayList<Block> artistBlockchain = new ArrayList<Block>();
	
	public static int difficulty = 5;

	public static void createArtistBlock() {	
		//add our blocks to the blockchain ArrayList:
		
		System.out.println("Creating blocks for music artists");
		
		artistBlockchain.add(new Block("Hi I'm a new singer. Please follow my work if you like", "0"));
		System.out.println("Trying to Mine block for artist 1... ");
		artistBlockchain.get(0).mineBlock(difficulty);
		
		artistBlockchain.add(new Block("Yo I'm a guitarist. I play really well!",artistBlockchain.get(artistBlockchain.size()-1).hash));
		System.out.println("Trying to Mine block for artist 2... ");
		artistBlockchain.get(1).mineBlock(difficulty);
		
		artistBlockchain.add(new Block("Hey another musician this side. Enjoy!",artistBlockchain.get(artistBlockchain.size()-1).hash));
		System.out.println("Trying to Mine block for artist 3... ");
		artistBlockchain.get(2).mineBlock(difficulty);
		
		artistBlockchain.add(new Block("Sup! I am a newbie in music. Please support my work!",artistBlockchain.get(artistBlockchain.size()-1).hash));
		System.out.println("Trying to Mine block for artist 4... ");
		artistBlockchain.get(3).mineBlock(difficulty);
		
		artistBlockchain.add(new Block("Hi y'all! Check my music out!",artistBlockchain.get(artistBlockchain.size()-1).hash));
		System.out.println("Trying to Mine block for artist 5... ");
		artistBlockchain.get(4).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String artistBlockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(artistBlockchain);
		System.out.println("\nThe artist blockchain: ");
		System.out.println(artistBlockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < artistBlockchain.size(); i++) {
			currentBlock = artistBlockchain.get(i);
			previousBlock = artistBlockchain.get(i-1);
			
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
}