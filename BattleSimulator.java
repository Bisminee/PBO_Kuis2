public class BattleSimulator {

    public static String heroAttack(Hero hero, Enemy enemy, Skill skill) {
        int damage;
        if (skill != null) {
            damage = skill.calculateDamage(hero.getAttackPower());
        } else {
            damage = hero.basicAttack();
        }

        enemy.receiveDamage(damage);

        return hero.getName() + " menggunakan " + (skill != null ? skill.getSkillName() : "Basic Attack") +
               " dan memberikan " + damage + " damage!" +
               " (HP Musuh sekarang: " + enemy.getCurrentHP() + ")";
    }

    public static String enemyAttack(Enemy enemy, Hero hero) {
        int damage = enemy.basicAttack();
        hero.receiveDamage(damage);

        return enemy.getName() + " menyerang dan memberikan " + damage +
               " damage! (HP Hero sekarang: " + hero.getCurrentHP() + ")";
    }

    public static boolean isHeroDead(Hero hero) {
        return hero.getCurrentHP() <= 0;
    }

    public static boolean isEnemyDead(Enemy enemy) {
        return enemy.getCurrentHP() <= 0;
    }
    
    public static String runBattle(Hero hero, Enemy enemy, Skill skill) {
        StringBuilder log = new StringBuilder();
        
        int turn = 1;
        
        while (hero.getCurrentHP() > 0 && enemy.getCurrentHP() > 0) {
            log.append("=== Turn " + turn + " ===\n");
            
            // HERO ATTACK
            int heroDamage = (int) (hero.getAttackPower() * skill.getMultiplier());
            enemy.receiveDamage(heroDamage);
            log.append(hero.getName() + " menyerang menggunakan " + skill.getSkillName() +
            " dan memberikan " + heroDamage + " damage!\n");
            log.append("HP Enemy sekarang: " + enemy.getCurrentHP() + "\n");
            
            if (enemy.getCurrentHP() <= 0) {
                log.append("\nEnemy kalah!\n");
                break;
            }
            
            // ENEMY ATTACK BALIK
        hero.receiveDamage(enemy.getAttackPower());
        log.append(enemy.getName() + " menyerang balik! Hero menerima "
        + enemy.getAttackPower() + " damage!\n");
        log.append("HP Hero sekarang: " + hero.getCurrentHP() + "\n");
        
        if (hero.getCurrentHP() <= 0) {
            log.append("\nHero kalah!\n");
            break;
        }
        
        log.append("\n");
        turn++;
    }

    return log.toString();
}
}
