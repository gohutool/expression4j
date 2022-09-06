package com.exceoon.expression.extend;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

import junit.framework.TestCase;

public class TestByteBuffer extends TestCase {
	public void testGet()
	{
		ByteBuffer b = ByteBuffer.allocate(100);
			
		printByteBufferPositionInfo(b);
		
		b.put("1234567890".getBytes());
		//b.flip();			
		printByteBufferPositionInfo(b);
		
		byte b1 = b.get(0);
		byte b2 = b.get(1);
		
		System.out.println(b.get());
		System.out.println(b1 + "  " + b2);
		
		printByteBufferPositionInfo(b);		
		byte bytes[] = new byte[100];
		b.position(0);
		b.get(bytes, 0, 10);		
		System.out.println(new String(bytes, 0, 10));
		
		//b.position(b.limit());		
		//b.limit(100);
		b.put("abcde".getBytes());
		b.position(10);
		b.get(bytes, 0, 4);
		System.out.println(b.get());
		System.out.println(new String(bytes, 0, 4));
		
		printByteBufferPositionInfo(b);
	}
	
	public void testInteger()
	{
		int i = Integer.MAX_VALUE;
		
		i ++;
		
		System.out.println(i);
	}
	
	public void testPut()
	{
		ByteBuffer b = ByteBuffer.allocate(100);
		printByteBufferPositionInfo(b);
		b.put("1234567890".getBytes());
		printByteBufferPositionInfo(b);
		
		ByteBuffer b2 = ByteBuffer.allocate(100);
		b2.put(b);
		
		printByteBufferPositionInfo(b2);
	}
	
	public void testLimit()
	{
		ByteBuffer b = ByteBuffer.allocate(100);
		b.put("1234567890".getBytes());
		b.flip();
		printByteBufferPositionInfo(b);

		int olimit = b.limit();
		
		printByteBufferPositionInfo(b);
		b.limit(4);
		printByteBufferPositionInfo(b);
		b.limit(olimit);
		printByteBufferPositionInfo(b);
		
	}
	
	public void testCompact()
	{
		ByteBuffer b = ByteBuffer.allocate(16);
		printByteBufferPositionInfo(b);
		b.flip();
		printByteBufferPositionInfo(b);
		b.clear();
		
		byte[] a = {1,2,3,4,5,6,7,8};
		
		b.put(a);
		printByteBufferPositionInfo(b);
		a = new byte[]{1,2,3,4,5};
		b.put(a);		
		printByteBufferPositionInfo(b);
		
		b.compact();
		printByteBufferPositionInfo(b);
		
		b.put(a);
		printByteBufferPositionInfo(b);
		
		b.compact();
		printByteBufferPositionInfo(b);
	}
	
	private void printByteBufferPositionInfo(ByteBuffer b)
	{
		System.out.println("==========================");
		System.out.println("position : " + b.position());
		System.out.println("remain : " + b.remaining());
		System.out.println("lenght : " + b.limit());
	}
	
	public void testBitOp()
	{
		bitOp(129);
		bitOp(1);
	}
	
	public void testBit()
	{
		int n = 953;
		byte a = (byte) (n & 0xff >> 8);
		byte b = (byte) n;
		
		System.out.println((n&0xff>>8));
		
		System.out.println(a + "  " + b);
	}
	
	public void bitOp(int clientId)
	{
		byte[] b = int2bytes(clientId);
		
		int v = b[0]&0xff << 24 | b[1]&0xff << 16 | b[2]&0xff | (byte)b[3]&0xff;
		
		System.out.println(b[0] + "," + b[1] + ","  + b[2] + "," + b[3] + " = " + v);
	}
	
	public void testConcurrentLinkedQueue()
	{
		ConcurrentLinkedQueue<Object> q = new ConcurrentLinkedQueue<Object>();
		
		Object test = new Object();
		
		q.add(test);
		q.add(test);
		
		q.poll();
		q.poll();
		
		System.out.println(q.size());
		
	}
	
	public static int bytes2int(byte b[]) {
		return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16
				| (b[0] & 0xff) << 24;
	}
	
	public static byte[] int2bytes(int n) {
		byte b[] = new byte[4];
		b[0] = (byte) (n >> 24);
		b[1] = (byte) (n >> 16);
		b[2] = (byte) (n >> 8);
		b[3] = (byte) n;
		return b;
	}	
	
	public void testException()
	{
		try
		{
			try
			{
				throw new Exception("test");
			}
			finally
			{
				System.out.println("========= 1");
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage()+"========= 2");
			e.printStackTrace();
		}
	}
}
