public class Hero {
    private String name;
    private int level;
    private int maxHP;
    private int currentHP;
    private int attackPower;

    public Hero(String name, int level, int maxHP, int attackPower) {
        this.name = name;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
