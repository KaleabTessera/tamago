import tamago.*;

public interface ExportReceiverService extends ExportService {
    ReceiverService exportReceiverService() throws ServiceExportException;
}

