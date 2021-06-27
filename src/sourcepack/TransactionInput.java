package sourcepack;

import java.security.*;
import com.google.gson.*;
import java.util.*;
import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import org.bouncycastle.*;

public class TransactionInput {
	public String transactionOutputId; //Reference to TransactionOutputs -> transactionId
	public TransactionOutput UTXO; //Contains the Unspent transaction output
	
	public TransactionInput(String transactionOutputId) {
		this.transactionOutputId = transactionOutputId;
	}
}