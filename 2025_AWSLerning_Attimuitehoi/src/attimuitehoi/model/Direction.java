package attimuitehoi.model;

public enum Direction {
	/**
	 * 列挙子の定義(w:上、a:左、s:右、d:左)
	 * コンソールには矢印で
	 */
	    w("↑"),
	    a("←"),
	    s("↓"),
	    d("→")
	    ;

	    private String displaySymbol;

	    private Direction(String displaySymbol) {
	        this.displaySymbol = displaySymbol;
	    }

	    public String getDisplaySymbol() {
	        return this.displaySymbol;
	    }
	}
