package simpledb.log;

import static simpledb.file.Page.INT_SIZE;
import simpledb.file.*;
import java.util.Iterator;

/**
 * A class that provides the ability to move through the
 * records of the log file in reverse order.
 * 
 * @author Edward Sciore
 */
public class LogIterator implements Iterator<BasicLogRecord> {
   private Block blk;
   private Page pg = new Page();
   private int currentrec;
   
   /**
    * Creates an iterator for the records in the log file,
    * positioned after the last log record.
    * This constructor is called exclusively by
    * {@link LogMgr#iterator()}.
    */
   LogIterator(Block blk) {
      this.blk = blk;
      pg.read(blk);
      currentrec = pg.getInt(LogMgr.LAST_POS);
   }
   
   LogIterator(Block block, int forwardFlag) {
	      this.blk = new Block(block.fileName(), block.number() - 1);
	      moveToNextForwardBlock();
   }
   
   LogIterator(LogIterator backwardIterator) {
	   
   }
   
   /**
    * Determines if the current log record
    * is the earliest record in the log file.
    * @return true if there is an earlier record
    */
   public boolean hasNext() {
      return currentrec>0 || blk.number()>0;
   }
   
   /**
    * Moves to the next log record in reverse order.
    * If the current log record is the earliest in its block,
    * then the method moves to the next oldest block,
    * and returns the log record from there.
    * @return the next earliest log record
    */
   public BasicLogRecord next() {
      if (currentrec == 0) 
         moveToNextBlock();
    	  //moveToNextForwardBlock();
      currentrec = pg.getInt(currentrec);
      return new BasicLogRecord(pg, currentrec+INT_SIZE);
   }
   
   public void remove() {
      throw new UnsupportedOperationException();
   }
   
   /**
    * Moves to the next log block in reverse order,
    * and positions it after the last record in that block.
    */
   private void moveToNextBlock() {
      blk = new Block(blk.fileName(), blk.number()-1);
      pg.read(blk);
      currentrec = pg.getInt(LogMgr.LAST_POS);
   }
   
   public BasicLogRecord nextForward () {
	   /**
	   * Moves to the next log record in forward order.
	   * If the current log record is the latest in its block,
	   * then the method moves to the next block,
	   * and returns the log record from there.
	   * @return the next log record
	   */
	   if (currentrec == 0) 
	         moveToNextForwardBlock();
	      currentrec = pg.getInt(currentrec);
	      return new BasicLogRecord(pg, currentrec+INT_SIZE);
   }
   private void moveToNextForwardBlock () {
	   /**
	   * Moves to the next log block in forward order,
	   * and positions it at the first record in that block.
	   */
	   blk = new Block(blk.fileName(), blk.number()+1);
	   pg.read(blk);
	   currentrec = pg.getInt(LogMgr.LAST_POS);
	   
   }

public boolean hasNextForward() {
	// TODO Auto-generated method stub
	return false;
}
}
