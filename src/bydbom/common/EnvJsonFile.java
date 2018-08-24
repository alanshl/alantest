package bydbom.common;

public enum EnvJsonFile {
	
	BASICFILE("\\src\\bydbom\\configuration\\basic_parameters.json"),
	TESTFILE("\\src\\bydbom\\configuration\\test_parameters.json");
	
	private String desc;//��������

    /**
     * ˽�й���,��ֹ���ⲿ����
     * @param desc
     */
    private EnvJsonFile(String desc){
        this.desc=desc;
    }

    /**
     * ���巽��,��������,��������Ķ���û����
     * @return
     */
    public String getDesc(){
        return desc;
    }

    public static void main(String[] args){
        for (EnvJsonFile day:EnvJsonFile.values()) {
            System.out.println("name:"+day.name()+
                    ",desc:"+day.getDesc());
        }
    }

}
