package tools;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import fsm.Machine;
import fsm.Transit;
import graph.Graph;
import graph.INode;
import graph.IPath;
import graph.Node;
import graph.Path;
import jmsMQ.LocalConsumer;
import jmsMQ.LocalProducer;


public class GraphTool {
	
	

	public static void main(String[] args) {
		//test1();
		
		//test3();
		
		//test4();
		test5();
	}
	
	private static void test5() {
		//
		Graph a = readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\functiongraph.txt");
		a.setup();
		INode n = a.getNodeByName("StockEvent"); 
	    Set<Object> grey = new HashSet<>();
	    grey.add(n);
	    DFS(a,n,grey);
		
	}
	
	private static void test4()  {
	   Set<String> filenames = new HashSet<>();
	   String filename ="C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\unit.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    int state = 0;
		    
		    while ((line = br.readLine()) != null) {
		    	//System.out.println(line);
		    	if(line.indexOf("/")>-1) continue;
		    	filenames.add(line);
		    }
		}catch(Exception e) {
			
		}
		
		
		Graph a = readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\output.txt");
		a.setup();
		for(String f: filenames) {
			INode n = a.getNodeByName(f);  
	        Set<Object> grey = new HashSet<>();
	        grey.add(n);
	        DFS(a,n,grey);
		}

	}
	private static void test3()  {
		Graph a = readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\work.txt");
		a.setup();
		 INode n = a.getNodeByName("submitted");
		try {
			BFSWithTrace(a,n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void test1() {
		Graph a = readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\a.txt");
		Graph b = readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\b.txt");
        //Graph c = mergeMachineOnInOut(a,b,"t1","t2");
        //c.printSetup();
        //String file = GraphToString(c);
		a.setup();
        INode n = a.getNodeByName("a0");
        Set<Object> grey = new HashSet<>();
        grey.add(n);
        DFS(a,n,grey);
	}
	
	private static void test2()  {
		Graph a = readFromFile("C:\\Users\\jliang\\DEV_LAB\\ordering_eclipse\\zoomytest\\src\\fsm\\a.txt");
		a.setup();
		 INode n = a.getNodeByName("a0");
		try {
			BFSWithTrace(a,n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Graph joinMahineOn(Graph main, Graph second, String mainpoint, String secondpoint) {
		
		return main;
	}
	
	public static Graph[] splitGraphOnPath(Graph main, IPath path) {
				
		Graph[] gs = new Graph[2];
		
		
		// path cut from/to
		INode from = path.getFrom();
		INode to = path.getTo();
		
		// bfs search 
		Graph fromgraph = new Graph(main.getSetup_nodes(),main.getSetup_paths());
		fromgraph.delPath(path);
		fromgraph.delNode(to);
		
		return gs;
	}
	public static void walk(Graph main, INode n) {
		
	}
	
	public static void BFS(Graph main, INode n) {
		
		List<INode> black = new LinkedList<>();
		
		Queue<INode> grey = new LinkedList<INode>();
		grey.add(n);
		while(true) {
			INode chq = grey.poll();
			black.add(chq);
			if(chq == null) {
				break;
			}
			Set<IPath> ups = chq.getUp();
			Set<IPath> downs = chq.getDown();
			
			expore(black, grey, ups);
			expore(black, grey, downs);
		}
		
	}
	
	public static void BFSWithTrace(Graph main, INode n) throws InterruptedException {
		Env env = new Env();
		
		List<Object> black = new LinkedList<>();
		
		Queue<INode> grey = new LinkedList<INode>();
		grey.add(n);
		
		
		while(true) {
			INode chq = grey.poll();
			env.addObject(chq);
			black.add(chq);
			if(chq == null) {
				break;
			}
			Set<IPath> ups = chq.getUp();
			Set<IPath> downs = chq.getDown();
			
			exporeWithTrace(black, grey, ups,env);
			exporeWithTrace(black, grey, downs,env);
		}
		
		for(int i=0;i<20;i++) {
			LocalConsumer consum = new LocalConsumer();
			consum.setDestname("GraphTool");
			Thread t = new Thread(consum);
			t.setName(""+i);
			t.start();
			
		}
		
		
		
		for(Object o: env.getObjects()) {
			LocalProducer prod = new LocalProducer();
			prod.setdestname("GraphTool");
			if(o instanceof INode) {
				INode no = (INode)o;
				String stnode = "trace node " + no.toString();
				System.out.println(stnode);
				prod.setMessage(stnode);
	
			}else if(o instanceof IPath)
			{
				IPath po = (IPath)o;
				String path = "trace path " + po.toString();
				System.out.println(path);
				prod.setMessage(path);
			    //prod.send();
			}
			Thread t = new Thread(prod);
			t.start();
		}
	   

		
	}
	
	private static void jumpfrog(Object o) {
		if(o instanceof INode) {
			INode no = (INode)o;
			System.out.println("frog " + no.toString());
			Set<IPath> ups = no.getUp();
			Set<IPath> downs = no.getDown();
			ups.addAll(downs);
			IPath path = ups.stream().collect(Collectors.toList()).get(0);
			jumpfrog(path.getFrom());
			//jumpfrog(path.getTo());
		}
	}
	
	public static void DFS(Graph main, INode n,Set<Object> black) {
		Set<IPath> ups = n.getUp();
		Set<IPath> downs = n.getDown();
		ups.addAll(downs);
		for(IPath ch: ups) {
			if(black.contains(ch)) {
				continue;
			}else {
				black.add(ch);
			}
			//System.out.println("Path " + ch.getName() + " from " + ch.getFrom().getName() + " to " + ch.getTo().getName());
			System.out.println(ch.getName());
			INode from = ch.getFrom();
			INode to = ch.getTo();
			
			if(!black.contains(from)) {
				black.add(from);
				DFS(main,from,black);
			}
			if(!black.contains(to)) {
				black.add(to);
				DFS(main,to,black);
			}
			
		}
		
	}

	private static void expore(List<INode> grey, Queue<INode> qn, Set<IPath> paths) {
		for(IPath u: paths) {
			System.out.println(u.getName()+": " + "from " + u.getFrom().getName() + " to " + u.getTo().getName());
			INode fr = u.getFrom();
			INode to = u.getTo();
			if(!grey.contains(fr))
			   qn.add(fr);
			
			if(!grey.contains(to))
			   qn.add(to);
		}
	}
	
	private static void exporeWithTrace(List<Object> black, Queue<INode> qn, Set<IPath> paths,Env env) {
		for(IPath u: paths) {
			
			
			if(black.contains(u)) continue;
			env.addObject(u);
			black.add(u);
			System.out.println(u.getName()+": " + "from " + u.getFrom().getName() + " to " + u.getTo().getName());
			INode fr = u.getFrom();
			INode to = u.getTo();
			if(!black.contains(fr))
			   { qn.add(fr);
			    
			   }
			
			if(!black.contains(to))
			   { qn.add(to);
			     
			   }
		}
	}
	
	public static Graph mergeMachineOnInOut(Graph main, Graph sub, String in, String out) {
		IPath p1 = main.getPathByName(in);
		IPath p2 = main.getPathByName(out);
		
		INode to = sub.getNodeByName("b0");
		INode from = sub.getNodeByName("b1");
		
		p1.setTo(to);
		p2.setFrom(from);
		
		main.delNode(main.getNodeByName("a1"));
		
		for(INode n: sub.getSetup_nodes()) {
			main.addNode(n);
		}
		
		for(IPath p : sub.getSetup_paths()) {
			main.addPath(p);
		}
		
		main.setup();
		return main;
	}
	public static Graph readFromFile(String filename)  {
		
		Graph g = new Graph();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    int state = 0;
		    
		    while ((line = br.readLine()) != null) {
		      if(state == 0) {
		    	  
		    	  if(line.trim().equalsIgnoreCase("Path")) {
		    		  state = 1;
		    		  continue;
		    	  }else {
		    		  Node s = new Node(line);
		    		  g.addNode(s);
		    	  }
		    	  
		      }else {
		    	  if(line.isEmpty()) continue;
		    	  String[] segs = line.split("\\|");
		    	  String name = segs[0];
		    	  String from = segs[1];
		    	  String to = segs[2];
		    	 // String evt = segs[3];
		    	  
		    	  Path path = new Path(name);
		    	  path.setFrom(g.getNodeByName(from));
		    	  path.setTo(g.getNodeByName(to));
                  g.addPath(path);
		      }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		return g;
	}
	
	public static String GraphToString(Graph g) {
		StringBuilder sb = new StringBuilder();
		List<INode> ns = g.getSetup_nodes();
		for(INode n: ns) {
			String name = n.getName();
			sb.append(name);
			sb.append("\n");
		}
		
		sb.append("Path\n");
		List<IPath> ps = g.getSetup_paths();
		for(IPath p : ps) {
			String name = p.getName();
			String from = p.getFrom().getName();
			String to = p.getTo().getName();
			sb.append(name);
			sb.append(",");
			sb.append(from);
			sb.append(",");
			sb.append(to);
			sb.append("\n");
		}
		
		String tofile = sb.toString();
		return tofile;
	}

}

