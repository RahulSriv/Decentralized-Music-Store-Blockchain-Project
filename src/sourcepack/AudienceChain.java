package sourcepack;

import java.security.*;
import com.google.gson.*;
import java.util.*;
import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import org.bouncycastle.*;

public class AudienceChain {
	public static ArrayList<Block> audienceBlockchain = new ArrayList<Block>();
	
	public static int difficulty = 5;

	public static void createAudienceBlock() {	
		//add our blocks to the blockchain ArrayList:
		
		System.out.println("Creating blocks for music listeners");
		
		audienceBlockchain.add(new Block("Hi I'm enjoy pop and rock music. Looking for something new", "0"));
		System.out.println("Trying to Mine block for listener 1... ");
		audienceBlockchain.get(0).mineBlock(difficulty);
		
		audienceBlockchain.add(new Block("Yo I want to listen some retro songs!",audienceBlockchain.get(audienceBlockchain.size()-1).hash));
		System.out.println("Trying to Mine block for listener 2... ");
		audienceBlockchain.get(1).mineBlock(difficulty);
		
		audienceBlockchain.add(new Block("Hey another listener this side!",audienceBlockchain.get(audienceBlockchain.size()-1).hash));
		System.out.println("Trying to Mine block for listener 3... ");
		audienceBlockchain.get(2).mineBlock(difficulty);
		
		audienceBlockchain.add(new Block("Sup! I am looking for newbies in music.",audienceBlockchain.get(audienceBlockchain.size()-1).hash));
		System.out.println("Trying to Mine block for listener 4... ");
		audienceBlockchain.get(3).mineBlock(difficulty);
		
		audienceBlockchain.add(new Block("Hi y'all! Check my music out!",audienceBlockchain.get(audienceBlockchain.size()-1).hash));
		System.out.println("Trying to Mine block for listener 5... ");
		audienceBlockchain.get(4).mineBlock(difficulty);
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String audienceBlockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(audienceBlockchain);
		System.out.println("\nThe listeners blockchain: ");
		System.out.println(audienceBlockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < audienceBlockchain.size(); i++) {
			currentBlock = audienceBlockchain.get(i);
			previousBlock = audienceBlockchain.get(i-1);
			
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