package bai4;

import jdk.jfr.Description;

/*
* T1 tạo số ngẫu nhiên để giao cho thread khác, nếu tổng >= 1000 thì dừng chương trình
* Nếu số tạo ra chia hết cho 3 thì chuyển cho thread 2 để in bình phương số đó
* Nếu không chia hết cho 3 và chia hết cho 2 thì gừi cho thread 3 kiểm tra xem có chia hết cho 4 không và in kết quả.
*/

public class SharedData {
    private int randomNumber;
    private int total;
    private int flag;
    public SharedData(){
        flag = 1;
    }
    @Description("Tăng total mỗi khi T1 tạo số mới")
    public void plus(int value){
        total += value;
    }
    public boolean checkAvailable(){
        return total < 1000;
    }
    public int getRandomNumber() {
        return randomNumber;
    }
    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
        plus(randomNumber);
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
}
