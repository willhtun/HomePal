package willhtun.homepal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME_CUSTOMS = "customs_table";
    private static final String CUSTOM_INDEX = "ind";
    private static final String CUSTOM_NAME = "name";

    private static final String TABLE_NAME_DUEDATE = "dueDates_table";
    private static final String TYPE = "type";
    private static final String DUE_DATE = "duedate";
    private static final String COST = "cost";

    private static final String TABLE_NAME_HISTORY = "history_table";
    private static final String TYPE_MONTH = "type_month";
    private static final String PAID_DATE = "paid_date";
    private static final String PAID_AMOUNT = "paid_amount";
    private static final String PAID_ME = "paid_person0";
    private static final String PAID_P1 = "paid_person1";
    private static final String PAID_P2 = "paid_person2";
    private static final String PAID_P3 = "paid_person3";
    private static final String PAID_P4 = "paid_person4";
    private static final String PAID_P5 = "paid_person5";

    public DatabaseHelper(Context context) {
        super(context, "HomePal_db", null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_customs_table = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_CUSTOMS + " (" +
                CUSTOM_INDEX + " INTEGER PRIMARY KEY, " +
                CUSTOM_NAME + " TEXT)";
        db.execSQL(create_customs_table);

        String create_dueDates_table = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_DUEDATE + " (" +
                TYPE + " TEXT PRIMARY KEY, " +
                DUE_DATE + " INTEGER, " +
                COST + " DOUBLE)";
        db.execSQL(create_dueDates_table);

        String create_history_table = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_HISTORY + " (" +
                TYPE_MONTH + " TEXT UNIQUE, " +
                PAID_DATE + " INTEGER," +
                PAID_AMOUNT + " REAL," +
                PAID_ME + " INTEGER," +
                PAID_P1 + " INTEGER," +
                PAID_P2 + " INTEGER," +
                PAID_P3 + " INTEGER," +
                PAID_P4 + " INTEGER," +
                PAID_P5 + " INTEGER)";
        db.execSQL(create_history_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CUSTOMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DUEDATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HISTORY);
        onCreate(db);
    }

    public boolean addData_toCustoms(int i, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOM_INDEX, i);
        contentValues.put(CUSTOM_NAME, name);

        long dbinsert = db.replace(TABLE_NAME_CUSTOMS, null, contentValues);

        db.close();

        if (dbinsert < 0)
            return false;
        else
            return true;
    }

    public boolean addData_toDueDate(String typ, int dd, double c) {
        String type = typ + "_dd";
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPE, type);
        contentValues.put(DUE_DATE, dd);
        contentValues.put(COST, c);

        long dbinsert = db.replace(TABLE_NAME_DUEDATE, null, contentValues);

        db.close();

        if (dbinsert < 0)
            return false;
        else
            return true;
    }

    public void addData_toHistory(String typ, String mon, int pd, int personNum) {
    }

    public void addData_toHistory(String typ, String mon, int pd, int personNum, float price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (!checkIfRowExists(TABLE_NAME_HISTORY, TYPE_MONTH, typ+"_"+mon)) {
            contentValues.put(TYPE_MONTH, typ + "_" + mon);
            contentValues.put(PAID_DATE, 000000);
            contentValues.put(PAID_AMOUNT, 00.00);
            contentValues.put(PAID_ME, 0);
            contentValues.put(PAID_P1, 0);
            contentValues.put(PAID_P2, 0);
            contentValues.put(PAID_P3, 0);
            contentValues.put(PAID_P4, 0);
            contentValues.put(PAID_P5, 0);

            long dbinsert = db.replace(TABLE_NAME_HISTORY, null, contentValues);
            contentValues.clear();
        }

        switch (personNum) {
            case 0:
                db.execSQL("UPDATE " + TABLE_NAME_HISTORY + " SET " + PAID_ME + " = 1 WHERE " + TYPE_MONTH + " = '" + typ+"_"+mon + "'");
                db.execSQL("UPDATE " + TABLE_NAME_HISTORY + " SET " + PAID_DATE + " = " + pd + " WHERE " + TYPE_MONTH + " = '" + typ+"_"+mon + "'");
                db.execSQL("UPDATE " + TABLE_NAME_HISTORY + " SET " + PAID_AMOUNT + " = " + price + " WHERE " + TYPE_MONTH + " = '" + typ+"_"+mon + "'");
                break;
            case 1:
                db.execSQL("UPDATE " + TABLE_NAME_HISTORY + " SET " + PAID_P1 + " = 1 WHERE " + TYPE_MONTH + " = '" + typ+"_"+mon + "'");
                break;
            case 2:
                db.execSQL("UPDATE " + TABLE_NAME_HISTORY + " SET " + PAID_P2 + " = 1 WHERE " + TYPE_MONTH + " = '" + typ+"_"+mon + "'");
                break;
            case 3:
                db.execSQL("UPDATE " + TABLE_NAME_HISTORY + " SET " + PAID_P3 + " = 1 WHERE " + TYPE_MONTH + " = '" + typ+"_"+mon + "'");
                break;
            case 4:
                db.execSQL("UPDATE " + TABLE_NAME_HISTORY + " SET " + PAID_P4 + " = 1 WHERE " + TYPE_MONTH + " = '" + typ+"_"+mon + "'");
                break;
            case 5:
                db.execSQL("UPDATE " + TABLE_NAME_HISTORY + " SET " + PAID_P5 + " = 1 WHERE " + TYPE_MONTH + " = '" + typ+"_"+mon + "'");
            case 9:
                db.execSQL("UPDATE " + TABLE_NAME_HISTORY + " SET " + PAID_AMOUNT + " = " + price + " WHERE " + TYPE_MONTH + " = '" + typ+"_"+mon + "'");
                break;
            default:
                break;
        }
        db.close();
    }

    public boolean checkIfRowExists(String table, String row, String key) {
        SQLiteDatabase db_read = this.getReadableDatabase();
        Cursor read_c = db_read.rawQuery("SELECT * FROM " + table + " WHERE " + row + " = '" + key + "'", null);
        if(read_c.getCount() <= 0) {
            read_c.close();
            return false;
        }
        read_c.close();
        return true;
    }

    public String getMonthName(int m) {
        String name;
        switch(m) {
            case 1:
                name = "Jan"; break;
            case 2:
                name = "Feb"; break;
            case 3:
                name = "Mar"; break;
            case 4:
                name = "Apr"; break;
            case 5:
                name = "May"; break;
            case 6:
                name = "Jun"; break;
            case 7:
                name = "Jul"; break;
            case 8:
                name = "Aug"; break;
            case 9:
                name = "Sep"; break;
            case 10:
                name = "Oct"; break;
            case 11:
                name = "Nov"; break;
            case 12:
                name = "Dec"; break;
            default:
                name = "NUL"; break;
        }
        return name;
    }

    private int extractMonth(String s) {
        int i = 0;
        String output = "";
        while (s.charAt(i) != '_')
            i++;
        i++;
        while (s.charAt(i) != '_') {
            output += s.charAt(i);
            i++;
        }
        return Integer.parseInt(output);
    }

    private int extractYear(String s) {
        int i = 0;
        String output = "";
        while (s.charAt(i) != '_')
            i++;
        i++;
        while (s.charAt(i) != '_')
            i++;
        i++;
        for (int j = i; j < s.length(); j++)
            output += s.charAt(j);
        return Integer.parseInt(output);
    }

    public String getName_fromCustoms(int i) {
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = CUSTOM_INDEX+"='" + i + "'";

        Cursor c = db.query(
                TABLE_NAME_CUSTOMS, // a. table
                new String[] {CUSTOM_INDEX, CUSTOM_NAME}, // b. column names
                whereClause, // c. selections
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (c != null && c.moveToFirst()) {
            String data = c.getString(c.getColumnIndex(CUSTOM_NAME));
            c.close();
            return data;
        }
        else {
            c.close();
            return null;
        }
    }

    public int getDate_fromDueDate(String typ) {
        String type = typ + "_dd";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = TYPE+"='" + type + "'";

        Cursor c = db.query(
                TABLE_NAME_DUEDATE, // a. table
                new String[] {TYPE, DUE_DATE}, // b. column names
                whereClause, // c. selections
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (c != null && c.moveToFirst()) {
           int data = c.getInt(c.getColumnIndex(DUE_DATE));
            c.close();
            return data;
        }
        else {
            c.close();
            return -1;
        }
    }

    public double getCost_fromDueDate(String typ) {
        String type = typ + "_dd";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = TYPE+"='" + type + "'";

        Cursor c = db.query(
                TABLE_NAME_DUEDATE, // a. table
                new String[] {TYPE, COST}, // b. column names
                whereClause, // c. selections
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (c != null && c.moveToFirst()) {
            double data = c.getDouble(c.getColumnIndex(COST));
            c.close();
            return data;
        }
        else {
            c.close();
            return 00.00;
        }
    }

    public String[] getPaidDate_fromHistory(String typ) {
        String output[] = new String[] {"", "", "", "", "", "","", "", "", "", "", "", "", "", "", "","", "", "", ""};
        String key = typ + "%";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = TYPE_MONTH +" LIKE '" + key + "'";

        Cursor c = db.query(
                TABLE_NAME_HISTORY, // a. table
                new String[] {TYPE_MONTH, PAID_DATE, PAID_AMOUNT, PAID_ME, PAID_P1, PAID_P2, PAID_P3, PAID_P4, PAID_P5}, // b. column names
                whereClause, // c. selections
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (c != null && c.moveToLast()) {
            for (int i = 0; i < 20; i++) {
                Log.d("YYY", c.getString(1) + " " + String.valueOf(c.getFloat(2)) + " " + String.valueOf(c.getInt(3)));
                if (c.getInt(3) != 0) {
                    //output[i] = c.getString(1) + " " + c.getString(0);
                    output[i] = c.getString(1) + " " + getMonthName(extractMonth(c.getString(0))) + " '" + String.valueOf(extractYear(c.getString(0)) % 2000);
                    if (c.getString(1).length() == 1)
                        output[i] += "\u0009";
                    output[i] += "\u0009\u0009\u0009\u0009\u0009" + "$" + c.getFloat(2);
                    if (c.getInt(4) == 0 ||
                        c.getInt(5) == 0 ||
                        c.getInt(6) == 0 ||
                        c.getInt(7) == 0 ||
                        c.getInt(8) == 0)
                        output[i] = "u" + output[i];
                    for (int j = 4; j < c.getColumnNames().length - 5; j++) {
                        output[i] += " " + c.getString(j) + " ";
                    }
                }
                if (!c.moveToPrevious())
                    break;
            }
            c.close();
            return output;
        }
        else {
            c.close();
            return new String[] {"", "", "", "", "", "","", "", "", "", "", "", "", "", "", "","", "", "", ""};
        }
    }

    public String getMostRecentPrice_fromHistory (String typ) {
        String output = "";
        String key = typ + "%";
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = TYPE_MONTH +" LIKE '" + key + "'";

        Cursor c = db.query(
                TABLE_NAME_HISTORY, // a. table
                new String[] {TYPE_MONTH, PAID_DATE, PAID_AMOUNT, PAID_ME, PAID_P1, PAID_P2, PAID_P3, PAID_P4, PAID_P5}, // b. column names
                whereClause, // c. selections
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (c != null && c.moveToLast()) {
            if (c.getInt(3) != 0) {
                output = String.valueOf(c.getFloat(2));
            }
            c.close();
            return output;
        }
        else {
            c.close();
            return "";
        }
    }

    public boolean isDataExists_fromHistory(String typ, String monyr, int Person) {
        String key = typ + "_" + monyr;
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = TYPE_MONTH+"='" + key + "'";

        Cursor c = db.query(
                TABLE_NAME_HISTORY, // a. table
                new String[] {TYPE_MONTH, PAID_ME, PAID_P1, PAID_P2, PAID_P3, PAID_P4, PAID_P5}, // b. column names
                whereClause, // c. selections
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (c != null && c.moveToFirst()) {
            int data = c.getInt(c.getColumnIndex("paid_person" + Person));
            c.close();
            if (data == 1)
                return true;
            else
                return false;
        }
        else {
            c.close();
            return false;
        }
    }
}
