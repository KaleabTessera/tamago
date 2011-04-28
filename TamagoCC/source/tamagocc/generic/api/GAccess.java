package tamagocc.generic.api;

public interface GAccess extends GObject {
	boolean canRead();
	boolean canWrite();
}
