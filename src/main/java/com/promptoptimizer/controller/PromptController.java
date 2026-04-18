@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PromptController {

    private final OptimizedAIService service;
    private final PromptHistoryRepository repo;

    public PromptController(OptimizedAIService service, PromptHistoryRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    // ✅ FIX FOR /api/
    @GetMapping("/")
    public String apiHome() {
        return "API Working ✅";
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody Map<String, String> req) {
        return service.process(req);
    }

    @GetMapping("/history")
    public List<PromptHistory> getHistory() {
        return repo.findAll();
    }
}
