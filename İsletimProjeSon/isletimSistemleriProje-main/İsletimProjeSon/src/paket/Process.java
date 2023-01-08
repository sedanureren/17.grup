package paket;

public class Process {
	
	    int Id;
	    int gelisZaman;
	    int calismaZaman;
	    int priority;
	    int asim;
	    public int getId() {
	        return Id;
	    }

	    public void setId(int id) {
	        Id = id;
	    }
	    
	    public int getgelisZaman() {
	        return gelisZaman;
	    }
	    public void setgelisZaman (int varisZamani) {
	        this.gelisZaman = varisZamani;
	    }
	    
	    public int getcalismaZaman() {
	        return gelisZaman;
	    }
	    
	    public void setcalismaZaman(int calismazaman) {
	        this.calismaZaman = calismazaman;
	    }
	    
	   
	   

	    public int getpriority() {
	        return priority;
	    }

	    public void setpriority(int onclk) {
	        this.priority = onclk;
	    }

	    public int getasim() {
	        return asim;
	    }

	    public void setasim(int asimZaman) {
	        this.asim = asimZaman;
	    }


	   

	    @Override
	    public String toString() {
	        return "process [Id=" + Id + ", varisZamani=" + gelisZaman + ", calismaZamani=" + gelisZaman + ", oncelik="
	                + priority + ", asimZamani="+ asim + "]";
	    }
	   

	    public Process (int[] list ){

	        this.Id= list[0];
	        this.gelisZaman= list[1];
	        this.priority=list[2];
	        this.calismaZaman=list[3];
	        this.asim = list[4];


	    }


}
