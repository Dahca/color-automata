package serde;
import java.io.*;
public class SimFileManager
{
	public static void main(String[] args)
	{
		write(new int[10][10][10], "test.ino");
	}
	public static void write( Object obj, String filename )
	{
		try{
			FileOutputStream out = new FileOutputStream(filename+".class");
			ObjectOutputStream obj_out = new ObjectOutputStream(out);
			obj_out.writeObject(obj.getClass());
			obj_out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		String output ="";// write(obj,filename,"");
		try{
			FileWriter out = new FileWriter(new File(filename));
			out.write(output);
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private static String write( Object obj, String filename, String output_string )
	{
		if( obj instanceof Object[][] )
		{
			Object[][] arr = (Object[][])obj;
			System.out.println("DING");
			for( int i = 0; i < arr.length; i++)
			{
				write(arr[i], filename);
			}
		}
		else if( obj instanceof Object[] )
		{
			
		}
		return output_string;
	}
}
