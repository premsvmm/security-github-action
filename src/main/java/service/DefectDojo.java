package service;

public interface DefectDojo {
    public Integer createEngagement(String name, String branchTag, Integer product);

    public Integer getProductId();

    public void uploadScan(String scanType,Integer engagementId,String file);

    public String getTestRunName();
}
