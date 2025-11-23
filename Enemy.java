public class Enemy {
    private String name;
    private int maxHP;
    private int currentHP;
    private int attackPower;

    public Enemy(String name, int maxHP, int attackPower) {
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void receiveDamage(int damage) {
        currentHP = Math.max(0, currentHP - damage);
    }

    public int basicAttack() {
        return attackPower;
    }
}
