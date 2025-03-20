package infrastructure
data class ApplicationInputDto(
    val factorySizeDto: FactorySizeDto,
    val robots: List<Pair<StatusDto, List<InstructionDto>>>) {
}