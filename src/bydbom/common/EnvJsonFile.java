package bydbom.common;

public enum EnvJsonFile {
	
	BASICFILE("\\src\\bydbom\\configuration\\basic_parameters.json"),
	TESTFILE("\\src\\bydbom\\configuration\\test_parameters.json");
	
	private String desc;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    private EnvJsonFile(String desc){
        this.desc=desc;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
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
