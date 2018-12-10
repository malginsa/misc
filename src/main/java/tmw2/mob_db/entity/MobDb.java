package tmw2.mob_db.entity;

import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobDb {

    public static final String MOB_DB_FILE_NAME = "/home/msa/dev/tmw2/serverdata/db/re/mob_db.conf";
    private static Pattern SPRITE_NAME = Pattern.compile("^\\s*SpriteName:\\s+\"(\\w+)\"\\s*$");
    private static Pattern DROPS_STARTER = Pattern.compile("^\\s*Drops:\\s*\\{\\s*$");
    private static Pattern END_LIST = Pattern.compile("^\\s*}\\s*$");
    private static Pattern DROP = Pattern.compile("^\\s*(\\S+):\\s*(\\d+)\\s*$");

    List<Drop> drops = new ArrayList<>();
    List<String> items = new ArrayList<>();
    List<String> mobs = new ArrayList<>();
    Map<String, List<Drop>> dropsByItem = new HashMap<>();
    Map<String, List<Drop>> dropsByMob = new HashMap<>();

    private static class Drop {
        private String nameItem;
        private String nameMob;
        private int probability;
        public Drop(String nameItem, String nameMob, int probability) {
            this.nameItem = nameItem;
            this.nameMob = nameMob;
            this.probability = probability;
        }
        public String getItemName() {return nameItem;}
        public String getMobName() {return nameMob;}
        public int getProbability() {return probability;}
        @Override
        public String toString() { return "Drop{" + " nameItem=" + nameItem + " nameMob=" + nameMob + " probability=" + probability +"}\n"; }
    }

    private void readMobDB() throws IOException {

        List<String> lines = Files.readLines(
                new File(MOB_DB_FILE_NAME),
                Charset.forName("utf-8"));

        boolean inDropsList = false;
        String currentMobName = null;

        Set<String> itemsSet = new TreeSet<>();
        Set<String> mobsSet = new TreeSet<>();

        for (String line : lines) {

            Matcher matcher = SPRITE_NAME.matcher(line);
            if (matcher.matches()) {
                currentMobName = matcher.group(1);
                mobsSet.add(currentMobName);
                continue;
            }

            matcher = DROPS_STARTER.matcher(line);
            if (matcher.matches()) {
                inDropsList = true;
                continue;
            }

            matcher = END_LIST.matcher(line);
            if (matcher.matches()) {
                inDropsList = false;
                continue;
            }

            matcher = DROP.matcher(line);
            if (matcher.find() && inDropsList) {
                String itemName = matcher.group(1);
                String probability = matcher.group(2);
                drops.add(new Drop(itemName, currentMobName, Integer.parseInt(probability)));
                itemsSet.add(itemName);
                continue;
            }
        }

        items.addAll(itemsSet);
        mobs.addAll(mobsSet);
    }

    private Map<String, List<Drop>> createDropsByItem() {
        Map<String, List<Drop>> dropsByItem = new HashMap<>();
        for (Drop drop : drops) {
            String itemName = drop.getItemName();
            List<Drop> listOfDrops = dropsByItem.get(itemName);
            if (listOfDrops == null) {
                listOfDrops = new ArrayList<>();
                dropsByItem.put(itemName, listOfDrops);
            }
            listOfDrops.add(drop);
        }
        return dropsByItem;
    }

    private Map<String, List<Drop>> createDropsByMob() {
        Map<String, List<Drop>> dropsByMobs = new HashMap<>();
        for (Drop drop : drops) {
            String mobName = drop.getMobName();
            List<Drop> listOfDrops = dropsByMobs.get(mobName);
            if (listOfDrops == null) {
                listOfDrops = new ArrayList<>();
                dropsByMobs.put(mobName, listOfDrops);
            }
            listOfDrops.add(drop);
        }
        return dropsByMobs;
    }

    public void init() throws IOException {
        readMobDB();
        dropsByItem = createDropsByItem();
        dropsByMob = createDropsByMob();
    }

    public Map<String, Double> askDropsByItem(String requestedItem) {
        Map<String, Double> result = new HashMap<>();
        List<Drop> drops = dropsByItem.get(requestedItem);
        if (drops == null) return result;
        for (Drop drop : drops) {
            String mobName = drop.getMobName();
            Double currentProbability = 0.;
            if (result.containsKey(mobName)) currentProbability = result.get(mobName);
            result.put(mobName, currentProbability + drop.getProbability()/100.);
        }
        return result;
    }

    public Map<String, Double> askDropsByMob(String requestedMob) {
        Map<String, Double> result = new HashMap<>();
        List<Drop> drops = dropsByMob.get(requestedMob);
        if (drops == null) return result;
        for (Drop drop : drops) {
            String itemName = drop.getItemName();
            Double currentProbability = 0.;
            if (result.containsKey(itemName)) currentProbability = result.get(itemName);
            result.put(itemName, currentProbability + drop.getProbability()/100.);
        }
        return result;
    }

    public List<String> lookupItem(String searchString) {
        List<String> result = new ArrayList<>();
        for (String item : items) {
            if (StringUtils.containsIgnoreCase(item, searchString)) result.add(item);
        }
        return result;
    }

    public List<String> lookupMob(String searchString) {
        List<String> result = new ArrayList<>();
        for (String mob : mobs) {
            if (StringUtils.containsIgnoreCase(mob, searchString)) result.add(mob);
        }
        return result;
    }

    public Map<String, Double> lookupAndAsk(String searchString) {
        List<String> items = this.lookupItem(searchString);
        if (items.size() == 1) return this.askDropsByItem(items.get(0));
        if (items.size() > 1) return new HashMap<String, Double>(){{put("...", 0.);}};
        if (items.size() == 0) return new HashMap<>();
        return new HashMap<>();
    }

    public static void main(String[] args) throws IOException {

        MobDb mobDb = new MobDb();
        mobDb.init();

//        System.out.println(mobDb.lookupItem("Biscuit"));
//        System.out.println(mobDb.lookupMob("Fafi"));

        System.out.println(mobDb.askDropsByItem("SilverOre"));
//        System.out.println(mobDb.askDropsByMob("Snake"));

//        System.out.println(mobDb.lookupAndAsk("PileOfAsh"));
//        System.out.println(mobDb.lookupAndAsk("CloverPatch"));
    }
}
