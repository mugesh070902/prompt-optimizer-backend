@PostMapping("/login")
public Map<String, Object> login(@RequestBody Map<String, String> req) {

    User user = service.login(req.get("email"), req.get("password"));

    Map<String, Object> res = new HashMap<>();

    if (user != null) {
        String token = JwtUtil.generateToken(user.getEmail());

        res.put("token", token);
        res.put("email", user.getEmail());
        return res;
    }

    res.put("error", "Invalid credentials");
    return res;
}
