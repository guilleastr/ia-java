package printer;

import java.io.PrintStream;

import static java.lang.String.format;

public final class PrettyPrinterInteger {

    private static final char BORDER_KNOT = '+';
    private static final char HORIZONTAL_BORDER = '-';
    private static final char VERTICAL_BORDER = '|';

    private static final int DEFAULT_AS_NULL =-1;

    private final PrintStream out;
    private final int asNull;

    public PrettyPrinterInteger(PrintStream out) {
        this(out, DEFAULT_AS_NULL);
    }

    public PrettyPrinterInteger(PrintStream out, int asNull) {
        if ( out == null ) {
            throw new IllegalArgumentException("No print stream provided");
        }

        this.out = out;
        this.asNull = asNull;
    }

    public void print(int[][] table) {
        if ( table == null ) {
            throw new IllegalArgumentException("No tabular data provided");
        }
        if ( table.length == 0 ) {
            return;
        }
        final int[] widths = new int[getMaxColumns(table)];
        adjustColumnWidths(table, widths);
        printPreparedTable(table, widths, getHorizontalBorder(widths));
    }



    private void printPreparedTable(int[][] table, int widths[], String horizontalBorder) {
        final int lineLength = horizontalBorder.length();
        out.println(horizontalBorder);
        for ( final int[] row : table ) {
            if ( row != null ) {

                out.println(getRow(row, widths, lineLength));
                out.println(horizontalBorder);
            }
        }
    }

    private String getRow(int[] row, int[] widths, int lineLength) {
        final StringBuilder builder = new StringBuilder(lineLength).append(VERTICAL_BORDER);
        final int maxWidths = widths.length;
        for ( int i = 0; i < maxWidths; i++ ) {
            if(getCellValue(safeGet(row, i, -1))==Integer.MAX_VALUE){
                builder.append(padRight("INF", widths[i])).append(VERTICAL_BORDER);
            }else {
                builder.append(padRight(getCellValue(safeGet(row, i, -1)), widths[i])).append(VERTICAL_BORDER);
            }
        }
        return builder.toString();
    }

    private String getHorizontalBorder(int[] widths) {
        final StringBuilder builder = new StringBuilder(256);
        builder.append(BORDER_KNOT);
        for ( final int w : widths ) {
            for ( int i = 0; i < w; i++ ) {
                builder.append(HORIZONTAL_BORDER);
            }
            builder.append(BORDER_KNOT);
        }
        return builder.toString();
    }

    private int getMaxColumns(int[][] rows) {
        int max = 0;
        for ( final int[] row : rows ) {
            if ( row != null && row.length > max ) {
                max = row.length;
            }
        }
        return max;
    }

    private void adjustColumnWidths(int[][] rows, int[] widths) {
        for ( final int[] row : rows ) {
            if ( row != null ) {
                for ( int c = 0; c < widths.length; c++ ) {
                    final int cv = getCellValue(safeGet(row, c, asNull));
                     int l=0;
                    if(cv==Integer.MAX_VALUE){
                         l = "INF".length();
                    }else {
                        l = String.valueOf(cv).length();
                    }
                    if ( widths[c] < l ) {
                        widths[c] = l;
                    }
                }
            }
        }
    }

    private static String padRight(int s, int n) {
        return format("%1$-" + n + "s", s);
    }

    private static String padRight(String s, int n) {
        return format("%1$-" + n + "s", s);
    }

    private static int safeGet(int[] array, int index, int defaultValue) {
        return index < array.length ? array[index] : defaultValue;
    }

    private int getCellValue(int value) {
        return value;
    }

}