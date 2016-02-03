package noheaphelper;

import javax.realtime.*;

public class NoHeapHelper extends RealtimeThread 
{
    private Class runnable = null;
    private SchedulingParameters sched = null;
    private ReleaseParameters rel = null;
    private MemoryArea mem = null;
    private MemoryParameters memP = null;
    private ProcessingGroupParameters pgP = null;
    
    public NoHeapHelper() 
    {
        this.mem = ImmortalMemory.instance();
    }

    public NoHeapHelper(Class runnable) 
    {
        this(); 
        this.runnable = runnable;
    }

    public NoHeapHelper(MemoryArea mem) 
        throws Exception 
    {
        this.mem = mem;
        if ( mem instanceof HeapMemory )
            throw new Exception ( "MemoryArea must be ImmortalMemory or ScopedMemory");
        if ( MemoryArea.getMemoryArea(mem) instanceof HeapMemory )
            throw new Exception ( "MemoryArea created on the heap");
    }

    public NoHeapHelper(MemoryArea mem, Class runnable)
        throws Exception
    {
        this(mem); 
        this.runnable = runnable;
    }

    public NoHeapHelper(SchedulingParameters sched, ReleaseParameters rel,
                        MemoryParameters mp, MemoryArea mem, Class runnable)
            
        throws Exception
    {
        this(mem, runnable); 
        this.sched = sched;
        this.rel = rel;
        this.memP = mp;
    }

    //////////////////////////////////////////////////////////////////////////
    
    public void setRunnable(Class runnable)
    {
        this.runnable = runnable;
    }

    public void setSchedulingParams(SchedulingParameters sched)
    {
        this.sched = sched;
    }
    
    public void setReleaseParams(ReleaseParameters rel)
    {
        this.rel = rel;
    }

    public void setMemParams(MemoryParameters memP)
    {
        this.memP = memP;
    }

    public void setProcessingGroupParams(ProcessingGroupParameters pgP)
    {
        this.pgP = pgP;
    }

    static class NHRTStarter
            implements Runnable
    {
        public Class runnable = null;
        public SchedulingParameters sched = null;
        public ReleaseParameters rel = null;
        public MemoryArea mem = null;
        public MemoryParameters memP = null;
        public ProcessingGroupParameters pgP = null;
        
        NHRTStarter() { }
        
        public void run() {
            try {
                System.out.println("NHRTStarter run() called" );
                System.out.println("Executing in memory area " +
                        mem.toString() );
                
                // Create the runnable and the NHRT and start it
                Runnable r = (Runnable)runnable.newInstance();

                // Clone any NHRT constructor parameters
                // in the current non-heap memory context.
                // This includes SchedulingParameters,
                // ReleaseParameters, and Memoryparameters.
                // ProcessingGroupParameters is not yet
                // implemented by Java RTS
                PriorityParameters sp = null;
                ReleaseParameters rp = null;
                MemoryParameters mp = null;
                ProcessingGroupParameters pgp = null;
                if ( sched != null )
                    sp = (PriorityParameters)sched.clone();

                if ( rel != null ) {
                    if ( rel instanceof PeriodicParameters )
                        rp = (PeriodicParameters)rel.clone();
                    else if ( rel instanceof AperiodicParameters )
                        rp = (AperiodicParameters)rel.clone();
                    else
                        rp = (SporadicParameters)rel.clone();                    
                }

                if ( memP != null )
                    mp = (MemoryParameters)memP.clone();

                if ( pgP != null )
                    pgp = (ProcessingGroupParameters)pgP.clone();

                NoHeapRealtimeThread nhrt = 
                    new NoHeapRealtimeThread(
                        sp, rp, 
                        mp, getCurrentMemoryArea(), 
                        null, r);
                
                nhrt.start();
                
            }
            catch ( Exception e ) {
                e.printStackTrace();;
            }
        }
    }
    
    static NHRTStarter starter = new NHRTStarter();
    
    public void run()
    {
        try {
          synchronized( starter ) {
            starter.runnable = this.runnable;
            starter.rel = this.rel;
            starter.sched = this.sched;
            starter.mem = mem;
            starter.memP = this.memP;
            starter.pgP = this.pgP;
            mem.enter(starter);
          }
        }
        catch ( Exception e ) {
            e.printStackTrace();;
        }
    }
}
