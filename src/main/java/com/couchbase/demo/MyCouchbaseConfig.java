@Configuration
@EnableCouchbaseRepositories(basePackages={"com.couchbase.demo"})
public class MyCouchbaseConfig extends AbstractReactiveCouchbaseConfiguration  {

    @Override
    public String getConnectionString() {
        return "couchbase://127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "Administrator";
    }

    @Override
    public String getPassword() {
        return "Administrator";
    }

    @Override
    public String getBucketName() {
        return "travel-sample";
    }

    @Override
    protected CouchbaseEnvironment getEnvironment() {
        return DefaultCouchbaseEnvironment.builder()
            .connectTimeout(10000)
            .kvTimeout(10000)
            .queryTimeout(10000)
            .viewTimeout(10000)
            .build();
    }

    @Override
    public RxJavaCouchbaseTemplate reactiveCouchbaseTemplate() throws Exception {
        RxJavaCouchbaseTemplate template = super.reactiveCouchbaseTemplate();
        template.setWriteResultChecking(WriteResultChecking.LOG);
        return template;
    }

    //this is for dev so it is ok to auto-create indexes
    @Override
    public IndexManager indexManager() {
        return new IndexManager();
    }

    @Override
    protected Consistency getDefaultConsistency() {
        return Consistency.READ_YOUR_OWN_WRITES;
    }

}