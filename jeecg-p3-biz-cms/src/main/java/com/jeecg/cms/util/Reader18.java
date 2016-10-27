/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jeecg.cms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import com.jeecg.cms.entity.ErpProduct;

/**
 * 
 * @author zoudeyong
 */
public class Reader18 {
	static {
		// System.setProperty( "java.library.path", ""+Reader18.class
		// .getProtectionDomain().getCodeSource().getLocation());
		// System.out.println(System.getProperty("java.library.path"));
		// String
		// s=""+Reader18.class.getProtectionDomain().getCodeSource().getLocation();
		// System.out.println("Reader18初始化");
		// System.out.println(System.getProperty("java.version"));
		// System.loadLibrary("UHF_Reader18");
	}

	public final static String ANNTENNA1 = "antenna1";
	public final static String ANNTENNA2 = "antenna2";
	public final static String ANNTENNA3 = "antenna3";
	public final static String ANNTENNA4 = "antenna4";

	public native int[] OpenComPort(int[] arr);

	public native int[] AutoOpenComPort(int[] arr);

	public native int CloseComPort();

	public native int[] OpenNetPort(int addr, int Port, String IPaddr);

	public native int CloseNetPort(int Port);

	public native int CloseSpecComPort(int Frmhandle);

	public native int[] GetReaderInformation(int[] arr);

	public native int SetWGParameter(int[] arr);

	public native int[] ReadActiveModeData(int[] arr);

	public native int SetWorkMode(int[] arr);

	public native int[] GetWorkModeParameter(int[] arr);

	public native int BuzzerAndLEDControl(int[] arr);

	public native int WriteComAdr(int[] arr);

	public native int SetPowerDbm(int[] arr);

	public native int Writedfre(int[] arr);

	public native int Writebaud(int[] arr);

	public native int WriteScanTime(int[] arr);

	public native int SetAccuracy(int[] arr);

	// EPC G2
	public native int[] Inventory_G2(int[] arr);

	public native int[] ReadCard_G2(int[] arr);

	public native int[] WriteCard_G2(int[] arr);

	public native int[] EraseCard_G2(int[] arr);

	public native int[] SetCardProtect_G2(int[] arr);

	public native int[] DestroyCard_G2(int[] arr);

	public native int[] WriteEPC_G2(int[] arr);

	public native int[] SetReadProtect_G2(int[] arr);

	public native int[] SetMultiReadProtect_G2(int[] arr);

	public native int[] RemoveReadProtect_G2(int[] arr);

	public native int[] CheckReadProtected_G2(int[] arr);

	public native int[] SetEASAlarm_G2(int[] arr);

	public native int[] CheckEASAlarm_G2(int[] arr);

	public native int[] LockUserBlock_G2(int[] arr);

	// 18000_6B
	public native int[] Inventory_6B(int[] arr);

	public native int[] inventory2_6B(int[] arr);

	public native int[] ReadCard_6B(int[] arr);

	public native int[] WriteCard_6B(int[] arr);

	public native int[] LockByte_6B(int[] arr);

	public native int[] CheckLock_6B(int[] arr);
	/*
	 * AutoOpenComPort():自动连接串口 功能描述：
	 * 该函数用于自动识别与读写器连接的串口并且执行初始化操作，然后通过连接串口和读写器以创建通信连接。数据传输协议默认是57600bps，8位数,
	 * 1停止位，没有奇偶校验位。 在调用其它函数之前，您必须先连接串口和读写器。 应用： int[]
	 * AutoOpenComPort(int[]arr); 参数： arr：输入数组，2个字节
	 * 第1个字节：远距离读写器的地址。以广播地址（0xFF）调用此函数。 第2个字节：用该值设置或更改串口通讯控件的波特率。 baudrate
	 * 实际波特率 0 9600bps 1 19200 bps 2 38400 bps 4 56000 bps 5 57600 bps 6 115200
	 * bps 返回：
	 * 如果该函数调用成功，返回一个数组，第一个字节表示函数执行结果，为0表示成功，返回非零值请查看其他返回值定义，返回的错误代码请查看错误代码定义，
	 * 第2个字节表示返回的端口号(COM1-COM9)，第3个字节表示读写器实际地址，第4个字节表示返回与读写器连接端口对应的句柄，
	 * 应用程序通过该句柄可以操作连接在相应端口的读写器。如果打开不成功，返回的句柄值为-1。 COM1-COM9的定义如下： #define COM1
	 * 1 #define COM2 2 #define COM3 3 #define COM4 4 #define COM5 5 #define
	 * COM6 6 #define COM7 7 #define COM8 8 #define COM9 9
	 */

	/*
	 * Inventory_G2 ()：G2询查命令 功能描述： 询查命令的作用是检查有效范围内是否有符合协议的电子标签存在。 应用： int[]
	 * Inventory_G2(int[]arr); 参数： arr：输入数组，2个字节。 第1个字节：远距离读写器的地址。
	 * 第2个字节：与读写器连接端口对应的句柄。 返回： 如果该函数调用成功，返回一个数组： 第1个字节的值 0x01 询查时间结束前返回 0x02
	 * 询查时间结束使得询查退出 0x03
	 * 如果读到的标签数量无法在一条消息内传送完，将分多次发送。如果Status为0x0D，则表示这条数据结束后，还有数据。 0x04
	 * 还有电子标签未读取，电子标签数量太多，MCU存储不了 返回其他值，请查看其他返回值定义，jisua返回的错误代码请查看错误代码定义。
	 * 第2个字节表示电子标签的张数，第3个字节表示返回的EPC字节数，
	 * 其余字节表示读到的电子标签的EPC数据，是一张标签的EPC长度+一张标签的EPC号，依此累加。每个电子标签EPC号高字在前，每一个字的最高位在前。
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// mock(null, Reader18.ANNTENNA1);
		String usage = "usage: java UHF.Reader18 [b|s] [antenna] \n b:begin scan\n s:stop scan\n antenna:which antenna to scan defined in config.properties";

		if (args.length < 2) {
			System.out.println(usage);
			System.exit(0);
		}

		if (!args[1].startsWith("antenna")) {
			System.out.println("antenna error\n" + usage);
			System.exit(0);
		}

		Properties pro = new Properties();
		File f = new File(Reader18.class.getClassLoader().getResource("").getPath() + "config.properties");
		// System.out.println(f.getAbsolutePath());

		FileInputStream in = new FileInputStream(f);
		pro.load(in);
		in.close();
		String comPort = pro.getProperty(args[1]);

		String location = pro.getProperty("working_dir");
		File workingDir = new File(location);
		if (!workingDir.isDirectory()) {
			workingDir.mkdirs();
		}
		File scaning = new File(location + "scaning_" + comPort + ".scan");

		if ("b".equals(args[0])) { // 开启扫描
			if (!scaning.exists()) {
				scaning.createNewFile();
			}
			List<String> scanedEpcIds = new ArrayList<String>();
			int cp = Integer.valueOf(comPort.replace("com", ""));
			while (scaning.exists()) {
				List<String> epcIds = getEpcIds(cp);
				System.out.println("scaning..." + epcIds.size());
				for (String epcId : epcIds) {
					if (!scanedEpcIds.contains(epcId)) {
						scanedEpcIds.add(epcId);
						FileWriter writer = new FileWriter(scaning, true);
						System.out.println(epcId);
						writer.write(epcId + "\n");
						writer.close();
					}
				}
				Thread.sleep(Integer.valueOf(pro.getProperty("scan_interval")));
			}
			System.out.println("scaning exist");
		}
		if ("s".equals(args[0])) { // 停止扫描
			List<String> scanedEpcIds = new ArrayList<String>();
			if (scaning.exists()) {
				FileReader fr = new FileReader(scaning);
				BufferedReader br = new BufferedReader(fr);
				String epcId = br.readLine();
				while (epcId != null) {
					if (!scanedEpcIds.contains(epcId)) { // 去重
						scanedEpcIds.add(epcId);
					}
					epcId = br.readLine();
				}
				br.close();
				fr.close();
			}
			while (scaning.exists()) {
				scaning.delete();
			}
			File result = new File(location + "result");
			if (!result.isDirectory()) {
				result.mkdirs();
			}
			File scaned = new File(location + "result" + File.separatorChar + "scaned_" + args[1] + ".txt");
			if (!scaned.exists()) {
				scaned.createNewFile();
			}
			FileWriter writer = new FileWriter(scaned, true);
			for (String _epcId : scanedEpcIds) {
				writer.write(_epcId + "\n");
			}
			writer.close();
			System.out.println("stop scan...don't forget delete'scaned_xx.txt' after store to db!");
		}
	}

	private static List<String> getEpcIds(int comport) throws Exception {
		Reader18 r18 = new Reader18();
		int[] ocp_response = r18.AutoOpenComPort(new int[] { comport, 0xFF, 5 });
		// for (int i = 0; i < ocp_response.length; i++) {
		// System.out.println("ocp_response["+i+"]:"+String.format("%02X",ocp_response[i]));
		// }
		if (ocp_response[0] != 0) {
			throw new Exception("OpenComPort error:" + String.format("%02X", ocp_response[0]));
		}
		int readerAddr = ocp_response[2];
		int windowHandler = ocp_response[3];
		int[] ig_response = r18.Inventory_G2(new int[] { readerAddr, windowHandler });
		// for (int i = 0; i < ig_response.length; i++) {
		// System.out.println("ig_response["+i+"]:"+String.format("%02X",
		// ig_response[i]));
		// }
		List<String> epcIds = new ArrayList<String>();
		if (ig_response[0] == 1) {
			for (int i = 3; i < ig_response.length; i++) {
				int length = ig_response[i]; // epcId长度
				String epcId = "";
				int j = i + 1; // 跳过长度，以便ID不记录
				while (j <= i + length) {
					epcId += String.format("%02X", ig_response[j]); // 根据长度记录id
					j++;
				}
				i = j - 1;
				// 写数据
				epcIds.add(epcId);
			}
		}
		r18.CloseComPort();
		return epcIds;
	}

	public static void funcStart(String antenna) throws Exception {
		List<String> pcIds = new ArrayList<String>();
		Properties pro = new Properties();
		String path = Reader18.class.getClassLoader().getResource("/").getPath().replaceAll("%20", " ");
		File f = new File(path + "config.properties");

		System.out.println(f.getPath());

		FileInputStream in = new FileInputStream(f);
		pro.load(in);
		in.close();
		String comPort = pro.getProperty(antenna);

		String location = pro.getProperty("working_dir");
		String reader = pro.getProperty("reader18_dir");
		File workingDir = new File(location);
		if (!workingDir.isDirectory()) {
			workingDir.mkdirs();
		}
		Process p = Runtime.getRuntime().exec("java UHF.Reader18 b " + antenna, null, new File(reader));
	}

	public static List<String> funcStop(String antenna) throws Exception {
		List<String> pcIds = new ArrayList<String>();
		Properties pro = new Properties();
		String path = Reader18.class.getClassLoader().getResource("/").getPath().replaceAll("%20", " ");
		File f = new File(path + "config.properties");

		FileInputStream in = new FileInputStream(f);
		pro.load(in);
		in.close();
		String comPort = pro.getProperty(antenna);

		String location = pro.getProperty("working_dir");
		String reader = pro.getProperty("reader18_dir");
		File workingDir = new File(location);
		if (!workingDir.isDirectory()) {
			workingDir.mkdirs();
		}
		File scaning = new File(location + "scaning_" + comPort + ".scan");
		Process p = Runtime.getRuntime().exec("java UHF.Reader18 s " + antenna, null, new File(reader));

		List<String> scanedEpcIds = new ArrayList<String>();
		if (scaning.exists()) {
			FileReader fr = new FileReader(scaning);
			BufferedReader br = new BufferedReader(fr);
			String epcId = br.readLine();
			while (epcId != null) {
				if (!scanedEpcIds.contains(epcId)) { // 去重
					scanedEpcIds.add(epcId);
				}
				epcId = br.readLine();
			}
			br.close();
			fr.close();
		}
		while (scaning.exists()) {
			scaning.delete();
		}
		File result = new File(location + "result");
		if (!result.isDirectory()) {
			result.mkdirs();
		}
		File scaned = new File(location + "result" + File.separatorChar + "scaned_" + antenna + ".txt");
		if (!scaned.exists()) {
			scaned.createNewFile();
		}
		FileWriter writer = new FileWriter(scaned, true);
		for (String _epcId : scanedEpcIds) {
			writer.write(_epcId + "\n");
		}
		writer.close();
		System.out.println("stop scan...don't forget delete'scaned_xx.txt' after store to db!");

		return scanedEpcIds;

		// List<String> pcIds = new ArrayList<String>();
		// Properties pro = new Properties();
		// String path =
		// Reader18.class.getClassLoader().getResource("/").getPath().replaceAll("%20",
		// " ");
		// File f = new File(path + "config.properties");
		//
		// FileInputStream in = new FileInputStream(f);
		// pro.load(in);
		// in.close();
		// String comPort = pro.getProperty(antenna);
		//
		// String location = pro.getProperty("working_dir");
		// File workingDir = new File(location);
		// if (!workingDir.isDirectory()) {
		// workingDir.mkdirs();
		// }
		// File scaning = new File(location + "scaning_" + comPort + ".scan");
		//
		// if ("b".equals(flag)) { // 开启扫描
		// if (!scaning.exists()) {
		// scaning.createNewFile();
		// }
		// List<String> scanedEpcIds = new ArrayList<String>();
		// int cp = Integer.valueOf(comPort.replace("com", ""));
		// while (scaning.exists()) {
		// List<String> epcIds = getEpcIds(cp);
		// System.out.println("scaning..." + epcIds.size());
		// for (String epcId : epcIds) {
		// if (!scanedEpcIds.contains(epcId)) {
		// scanedEpcIds.add(epcId);
		// FileWriter writer = new FileWriter(scaning, true);
		// System.out.println(epcId);
		// writer.write(epcId + "\n");
		// writer.close();
		// }
		// }
		// Thread.sleep(Integer.valueOf(pro.getProperty("scan_interval")));
		// }
		// System.out.println("scaning exist");
		// }
		// if ("s".equals(flag)) { // 停止扫描
		// List<String> scanedEpcIds = new ArrayList<String>();
		// if (scaning.exists()) {
		// FileReader fr = new FileReader(scaning);
		// BufferedReader br = new BufferedReader(fr);
		// String epcId = br.readLine();
		// while (epcId != null) {
		// if (!scanedEpcIds.contains(epcId)) { // 去重
		// scanedEpcIds.add(epcId);
		// }
		// epcId = br.readLine();
		// }
		// br.close();
		// fr.close();
		// }
		// while (scaning.exists()) {
		// scaning.delete();
		// }
		// File result = new File(location + "result");
		// if (!result.isDirectory()) {
		// result.mkdirs();
		// }
		// File scaned = new File(location + "result" + File.separatorChar +
		// "scaned_" + antenna + ".txt");
		// if (!scaned.exists()) {
		// scaned.createNewFile();
		// }
		// FileWriter writer = new FileWriter(scaned, true);
		// for (String _epcId : scanedEpcIds) {
		// writer.write(_epcId + "\n");
		// }
		// writer.close();
		// System.out.println("stop scan...don't forget delete'scaned_xx.txt'
		// after store to db!");
		//
		// FileReader fr = new FileReader(scaned);
		// BufferedReader br = new BufferedReader(fr);
		// String epcId = br.readLine();
		// while (epcId != null) {
		// pcIds.add(epcId);
		// epcId = br.readLine();
		// }
		// br.close();
		// fr.close();
		// }
		// return pcIds;
	}

	public static void deleteFile(String antenna) throws IOException {
		Properties pro = new Properties();
		String path = Reader18.class.getClassLoader().getResource("/").getPath().replaceAll("%20", " ");
		File f = new File(path + "config.properties");

		FileInputStream in = new FileInputStream(f);
		pro.load(in);
		in.close();
		String comPort = pro.getProperty(antenna);

		String location = pro.getProperty("working_dir");
		File scaned = new File(location + "result" + File.separatorChar + "scaned_" + antenna + ".txt");
		if (scaned.isFile() && scaned.exists()) {
			scaned.delete();
		}
	}

	public static List<String> mock(String string, String antenna) throws IOException {
		String location = "C:\\rfid\\workspace\\";
		File result = new File(location + "result");
		if (!result.isDirectory()) {
			result.mkdirs();
		}
		File scaned = new File(location + "result" + File.separatorChar + "scaned_" + antenna + ".txt");
		if (!scaned.exists()) {
			scaned.createNewFile();
		}
		FileWriter writer = new FileWriter(scaned, true);
		for (int i = 0; i < 10; i++) {
			String _epcId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			writer.write(_epcId + "\n");
		}
		writer.close();
		System.out.println("stop scan...don't forget delete'scaned_xx.txt' after store to db!");
		List<String> scanedEpcIds = new ArrayList<String>();
		FileReader fr = new FileReader(scaned);
		BufferedReader br = new BufferedReader(fr);
		String epcId = br.readLine();
		while (epcId != null) {
			scanedEpcIds.add(epcId);
			epcId = br.readLine();
		}
		br.close();
		fr.close();

		return scanedEpcIds;
	}
}
