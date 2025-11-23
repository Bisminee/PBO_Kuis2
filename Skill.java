public class Skill {
    private String skillName;
    private double multiplier;

    public Skill(String skillName, double multiplier) {
        this.skillName = skillName;
        this.multiplier = multiplier;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public int calculateDamage(int baseAttack) {
        return (int) (baseAttack * multiplier);
    }
}