package simpledb.log;

import simpledb.buffer.Buffer;
import simpledb.buffer.BufferAbortException;
import simpledb.buffer.BufferMgr;
import simpledb.file.Block;
import simpledb.server.SimpleDB;
import simpledb.tx.recovery.*;

public class TestLogMgr {

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
			// Pin a Block
			Buffer buff = basicBufferMgr.pin(blk1, true);
			// Getting the buffer to which the block has been assigned from the bufferPoolMap
			System.out.println("\nBUFFER MAP 1: " + basicBufferMgr.getMapping(blk1));
			System.out.println("Number of available buffers after Block 1 is pinned: " + basicBufferMgr.available());
			RecoveryMgr rm = new RecoveryMgr(123);
			System.out.println("Recovery Manager for Transaction ID 123: " + rm);
			rm.commit();
			
			rm.recover();
			
			//Sample setInt
			int lsn = rm.setInt(buff, 4, 1234);
			System.out.println("LSN after setInt: " + lsn);
			//Sample setString
			lsn = rm.setString(buff, 6, "block1_newval");
			System.out.println("LSN after setString: " + lsn);
			//buff.setInt(4, 1234, 123, lsn);
			//Flushing all transactions
			//basicBufferMgr.flushAll(123);
			//Using Log Record Iterator to print records .
			LogRecordIterator it = new LogRecordIterator();
			System.out.println("\nLOG RECORD ITERATOR AFTER BLOCK 1: " + it.nextForward());
			
			// Pin a Block
			buff = basicBufferMgr.pin(blk2, true);
			// Getting the buffer to which the block has been assigned from the bufferPoolMap
			System.out.println("\nBUFFER MAP 2: " + basicBufferMgr.getMapping(blk2));
			System.out.println("Number of available buffers after Block 2 is pinned: " + basicBufferMgr.available());
			rm = new RecoveryMgr(123);
			System.out.println("Recovery Manager for Transaction ID 123: " + rm);
			rm.commit();
			
			rm.recover();
			
			//Sample setInt
			lsn = rm.setInt(buff, 4, 4321);
			System.out.println("LSN after setInt: " + lsn);
			//Sample setString
			lsn = rm.setString(buff, 6, "block2_newval");
			System.out.println("LSN after setString: " + lsn);
			//Using Log Record Iterator to print records .
			it = new LogRecordIterator();
			System.out.println("LOG RECORD ITERATOR AFTER BLOCK 2: " + it.nextForward());
		} catch (BufferAbortException e) {
			System.out.println("\nBufferAbortException: " + e.getStackTrace());
		}
		
		
	}

}
