package simpledb.tx.recovery;

import simpledb.buffer.Buffer;
import simpledb.buffer.BufferAbortException;
import simpledb.buffer.BufferMgr;
import simpledb.file.Block;
import simpledb.server.SimpleDB;

public class TestRecoveryMgr {

	public static void main(String[] args) {
		// Init a simpleDB Client
		SimpleDB.init("ABC");
					
		// Step 1 - Creating a list of file-blocks
					
		Block blk1 = new Block("filename1", 1);
		Block blk2 = new Block("filename2", 2);
		// Creating a basicBufferMgr
		BufferMgr basicBufferMgr = SimpleDB.bufferMgr() ;
		System.out.println("Number of available buffers initially: " + basicBufferMgr.available());
		try {
			RecoveryMgr rm1 = new RecoveryMgr(1);
			System.out.println("Recovery Manager for Transaction ID 1: " + rm1);
			RecoveryMgr rm2 = new RecoveryMgr(2);
			System.out.println("Recovery Manager for Transaction ID 2: " + rm2);
			RecoveryMgr rm3 = new RecoveryMgr(3);
			System.out.println("Recovery Manager for Transaction ID 3: " + rm3);
			
			// Pin a Block
			Buffer buff1 = basicBufferMgr.pin(blk1, true);
			System.out.println("\nBUFFER MAP 1: " + basicBufferMgr.getMapping(blk1));
			System.out.println("Number of available buffers after Block 1 is pinned: " + basicBufferMgr.available());
			// Pin a Block
						Buffer buff2 = basicBufferMgr.pin(blk2, true);
			// Getting the buffer to which the block has been assigned from the bufferPoolMap
			System.out.println("\nBUFFER MAP 2: " + basicBufferMgr.getMapping(blk1));
			System.out.println("Number of available buffers after Block 2 is pinned: " + basicBufferMgr.available());
			

			
			
			//Sample setInt
			int lsn = rm1.setInt(buff1, 4, 10);
			buff1.setInt(4, 10, 1, lsn);
			System.out.println("LSN after setInt: " + lsn);
			System.out.println("Buffer 1 has the set new value: " + buff1.getInt(4));
			rm1.commit();
			//Sample setString
			lsn = rm2.setString(buff2, 5, "World");
			buff2.setString(4, "World", 1, lsn);
			System.out.println("LSN after setString: " + lsn);
			System.out.println("Buffer 2 has the set new value: " + buff2.getString(4));
			//Unpin
			basicBufferMgr.unpin(basicBufferMgr.getMapping(blk1));
			System.out.println("\nBUFFER MAP 1: " + basicBufferMgr.getMapping(blk1));
			System.out.println("Number of available buffers after Block 1 is pinned: " + basicBufferMgr.available());
			
			//Unpin
			basicBufferMgr.unpin(basicBufferMgr.getMapping(blk2));
			System.out.println("\nBUFFER MAP 2: " + basicBufferMgr.getMapping(blk2));
			System.out.println("Number of available buffers after Block 2 is pinned: " + basicBufferMgr.available());
			
			//Pinning again
			buff1 = basicBufferMgr.pin(blk1, true);
			System.out.println("\nBUFFER MAP 1: " + basicBufferMgr.getMapping(blk1));
			System.out.println("Buffer 1 has the changes reflected after unpin and pin: " + buff1.getInt(4));
			//Pinning again
			buff2 = basicBufferMgr.pin(blk2, true);
			System.out.println("\nBUFFER MAP 2: " + basicBufferMgr.getMapping(blk2));
			System.out.println("Buffer 2 has the changes reflected after unpin and pin: " + buff2.getString(4));
			
			rm1.commit();
			rm1.recover();
			rm2.recover();
			
		} catch (BufferAbortException e) {
			System.out.println("\nBufferAbortException: " + e.getStackTrace());
		}
		
		
	}

}
