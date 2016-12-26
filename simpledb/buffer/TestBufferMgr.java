package simpledb.buffer;

import simpledb.file.Block;
import simpledb.server.SimpleDB;

public class TestBufferMgr {

	public static void main(String[] args) {
		
			// Init a simpleDB Client
			SimpleDB.init("ABC");
			
			// STEP 1 - Creating a list of file-blocks
			
			System.out.println("\nSTEP 1 - Creating a list of file-blocks");
			Block blk1 = new Block("filename1", 1);
			Block blk2 = new Block("filename2", 2);
			Block blk3 = new Block("filename3", 3);
			Block blk4 = new Block("filename4", 4);
			Block blk5 = new Block("filename5", 5);
			Block blk6 = new Block("filename6", 6);
			Block blk7 = new Block("filename7", 7);
			Block blk8 = new Block("filename8", 8);
			Block blk9 = new Block("filename9", 9);
			
			// Creating a basicBufferMgr
			BufferMgr basicBufferMgr = SimpleDB.bufferMgr() ;
			
			// STEP 2 - Checking the number of available buffers initially
			System.out.println("\nSTEP 2 - Checking the number of available buffers initially");
			System.out.println("Number of available buffers initially: " + basicBufferMgr.available());
			System.out.println("\nSTEP 3 - Pinning buffers and checking available buffers after each pin");
			// STEP 3 - Pinning buffers and checking available buffers after each pin
			
			try {
				// Pin a Block
				basicBufferMgr.pin(blk1, true);
				System.out.println("\nPinning Block 1");
				// Getting the buffer to which the block has been assigned from the bufferPoolMap
				System.out.println("BUFFER MAP 1: " + basicBufferMgr.getMapping(blk1));
				System.out.println("Number of available buffers after Block 1 is pinned: " + basicBufferMgr.available());
				
				// Pin a Block
				basicBufferMgr.pin(blk2, true);
				System.out.println("\nPinning Block 2");
				// Getting the buffer to which the block has been assigned from the bufferPoolMap
				System.out.println("BUFFER MAP 2: " + basicBufferMgr.getMapping(blk2));
				System.out.println("Number of available buffers after Block 2 is pinned: " + basicBufferMgr.available());
				
				// Pin a Block
				basicBufferMgr.pin(blk3, true);
				System.out.println("\nPinning Block 3");
				// Getting the buffer to which the block has been assigned from the bufferPoolMap
				System.out.println("BUFFER MAP 3: " + basicBufferMgr.getMapping(blk3));
				System.out.println("Number of available buffers after Block 3 is pinned: " + basicBufferMgr.available());
				
				// Pin a Block
				basicBufferMgr.pin(blk4, true);
				System.out.println("\nPinning Block 4");
				// Getting the buffer to which the block has been assigned from the bufferPoolMap
				System.out.println("BUFFER MAP 4: " + basicBufferMgr.getMapping(blk4));
				System.out.println("Number of available buffers after Block 4 is pinned: " + basicBufferMgr.available());
				
				// Pin a Block
				basicBufferMgr.pin(blk5, true);
				System.out.println("\nPinning Block 5");
				// Getting the buffer to which the block has been assigned from the bufferPoolMap
				System.out.println("BUFFER MAP 5: " + basicBufferMgr.getMapping(blk5));
				System.out.println("Number of available buffers after Block 5 is pinned: " + basicBufferMgr.available());
				
				// Pin a Block
				basicBufferMgr.pin(blk6, true);
				System.out.println("\nPinning Block 6");
				// Getting the buffer to which the block has been assigned from the bufferPoolMap
				System.out.println("BUFFER MAP 6: " + basicBufferMgr.getMapping(blk6));
				System.out.println("Number of available buffers after Block 6 is pinned: " + basicBufferMgr.available());
				
				// Pin a Block
				basicBufferMgr.pin(blk7, true);
				System.out.println("\nPinning Block 7");
				// Getting the buffer to which the block has been assigned from the bufferPoolMap
				System.out.println("BUFFER MAP 7: " + basicBufferMgr.getMapping(blk7));
				System.out.println("Number of available buffers after Block 7 is pinned: " + basicBufferMgr.available());
				
				// Pin a Block
				basicBufferMgr.pin(blk8, true);
				System.out.println("\nPinning Block 8");
				// Getting the buffer to which the block has been assigned from the bufferPoolMap
				System.out.println("BUFFER MAP 8: " + basicBufferMgr.getMapping(blk8));
				System.out.println("Number of available buffers after Block 8 is pinned: " + basicBufferMgr.available());
				System.out.println("\nSTEP 4 - All buffers have been pinned. If pin request is made again, exception is thrown\n");
				// STEP 4 - All buffers have been pinned. If pin request is made again, exception is thrown.
				System.out.println("Pinning Block 9");
				//Pin a Block
				basicBufferMgr.pin(blk9, true);
				// Getting the buffer to which the block has been assigned from the bufferPoolMap
				System.out.println("BUFFER MAP 9: " + basicBufferMgr.getMapping(blk9));
			} catch(BufferAbortException e) {
				System.out.println("\nBufferAbortException: " + e.getStackTrace());
				System.out.println("-----------BUFFER IS FULL-----------");
				System.out.println("-----------UNPIN BUFFERS BEFORE PINNING A NEW BUFFER-----------");
			}
			
			// Getting the bufferPoolMap
			System.out.println("\nThe buffer pool map after pinning all the blocks\n");
			System.out.println("BUFFER POOL MAP: " + basicBufferMgr.getBufferPoolMap());
			System.out.println("\nSTEP 5 - Unpin blocks");
			// STEP 5 - Unpin blocks
			try {
				// Unpin a block
				System.out.println("\nUnpin Block 3");
				basicBufferMgr.unpin(basicBufferMgr.getMapping(blk3));
				// The buffer to which the block is assigned will now be null, as we have unpinned.
				System.out.println("BUFFER MAP 3: " + basicBufferMgr.getMapping(blk3));
				System.out.println("Number of available buffers after Block 3 is unpinned: " + basicBufferMgr.available());
				// Unpin a block
				System.out.println("\nUnpin Block 2");
				basicBufferMgr.unpin(basicBufferMgr.getMapping(blk2));
				System.out.println("BUFFER MAP 2: " + basicBufferMgr.getMapping(blk2));
				System.out.println("Number of available buffers after Block 2 is unpinned: " + basicBufferMgr.available());
			} catch(BufferAbortException e) {
				System.out.println("BufferAbortException: " + e.getStackTrace());
			}
			//Getting the bufferPoolMap
			System.out.println("\nThe buffer pool map after unpinning two blocks");
			System.out.println("BUFFER POOL MAP: " + basicBufferMgr.getBufferPoolMap());
			System.out.println("\nSTEP 6 - Pin the buffers again and check for the FIFO");
			//STEP 6 - Pin the buffers again and check for the FIFO
			try {
				//Pin a Block
				basicBufferMgr.pin(blk2, true);
				System.out.println("\nPinning Block 2");
				System.out.println("BUFFER MAP 2: " + basicBufferMgr.getMapping(blk2));
				System.out.println("Number of available buffers after Block 2 is pinned: " + basicBufferMgr.available());
				System.out.println("The buffer replaced next is: 2");
				//Pin a Block
				basicBufferMgr.pin(blk3, true);
				System.out.println("\nPinning Block 3");
				System.out.println("BUFFER MAP 3: " + basicBufferMgr.getMapping(blk3));
				System.out.println("Number of available buffers after Block 3 is pinned: " + basicBufferMgr.available());
				System.out.println("The buffer replaced next is: 3");
			} catch(BufferAbortException e) {
				System.out.println("BufferAbortException: " + e.getStackTrace());
			}
			System.out.println("\nThe final buffer pool map");
			System.out.println("BUFFER POOL MAP: " + basicBufferMgr.getBufferPoolMap());
		}
}
