package ru.reeson2003.model.characters.creatures;

/**
 * Created by reeson on 05.12.16.
 */
class Experience {
    private int level;
    private int experience;
    private int expToNextLevel;
    private int skillPoints;

    public Experience(int level) {
        this.level = level;
        experience = 0;
        expToNextLevel = ParametersConstants.EXP_COEFF;
        skillPoints = 0;
        calcExpToLevel(level);
    }

    public void addExperience(int experience) {
		this.experience += experience;
		levelUp();
    }

    private void calcExpToNextLevel() {
    	expToNextLevel = expToNextLevel * ParametersConstants.EXP_COEFF_ADDICTION / 1000;
    }
    private void calcExpToLevel(int level) {
        if (level > 0) {
            calcExpToNextLevel();
            calcExpToLevel(level-1);
        }
    }

    private void levelUp() {
    	if (this.experience > expToNextLevel) {
    		level++;
        	this.experience -= expToNextLevel;
		generateSkillPoint(); //
           	calcExpToNextLevel();
        	levelUp();
        }
    }
    private void generateSkillPoint() {
    	skillPoints += ParametersConstants.SKILL_POINTS_ADDICTION * 
						(level % ParametersConstants.EXTRA_SP_EACH_LVL == 0 ? 2 : 1); 
    }

    public int getLevel() {
        return level;
    }
    public int getExperience() {
        return experience;
    }
    public int getExpToNextLevel() {
        return expToNextLevel;
    }
    public int getSkillPoints() {
        return skillPoints;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "level=" + level +
                ", experience=" + experience +
                ", expToNextLevel=" + expToNextLevel +
                ", skillPoints=" + skillPoints +
                '}';
    }
}
