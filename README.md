# CSC540_F16_Project2
a) Buffer Management
Run the TestBufferMgr.java file inside the package simpledb.buffer.
Comments are mentioned step wise, also the output of the code has steps.
Sample Testing Scenario
1. Create a list of files-blocks.
2. Check the number of available buffers initially. All should be available as none of them
have been pinned yet.
3. Keep pinning buffers one by one and check the number of available buffers.
4. When all buffers have been pinned, if pin request is made again, throw an exception
6. Unpin a few buffers and see if you are still getting an exception or not.
7. Try to pin a new buffer again, and check your replacement policy while seeing which
currently unpinned buffer is replaced.

b) Log Management
Run the TestLogMgr.java file inside the package simpledb.log.
Sample Testing Scenario
1. Create a block and pin it to a buffer.
2. Create a recovery manager for a transaction (txid=123)
3. Use setInt and setString to set logs for the transaction with txid=123.
4. Use LogRecordIterator it.next() to see the logs are read in forward manner and prints
old and new value.
5. Use multiple blocks and repeat the above steps.

c) Recovery Management
Run the TestRecoveryMgr.java file inside the package simpledb.tx.recovery.
1. Create multiple RecoveryMgr for transactions with txid=1, txid=2 and txid=3
respectively.
2. Create a block, pin it to a buffer and write multiple logs( different types of LogRecord
eg. START, COMMIT, SETINT, SETSTRING) for each transaction.
3. Unpin and pin that buffer again to check changes are reflected in the buffer.
4. Commit one of the transactions.
5. Try to recover all transactions.

